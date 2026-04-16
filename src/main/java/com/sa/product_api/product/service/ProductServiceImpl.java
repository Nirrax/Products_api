package com.sa.product_api.product.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sa.product_api.producer.model.Producer;
import com.sa.product_api.producer.service.ProducerService;
import com.sa.product_api.product.dto.ProductDTO;
import com.sa.product_api.product.dto.ProductUpdateDTO;
import com.sa.product_api.product.exception.InvalidFiltersException;
import com.sa.product_api.product.exception.ProductNotFoundException;
import com.sa.product_api.product.model.Product;
import com.sa.product_api.product.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;
    private final ProducerService producerService;
    private final ObjectMapper objectMapper;

    public ProductServiceImpl(ProductRepository productRepository, ProducerService producerService,  ObjectMapper objectMapper) {
        this.productRepository = productRepository;
        this.producerService = producerService;
        this.objectMapper = objectMapper;
    }

    @Override
    public List<Product> getProducts(Map<String, String> filters) {
        if (filters.isEmpty()) return this.productRepository.findAll();
        try {
            String json = objectMapper.writeValueAsString(filters);
            return this.productRepository.findByAttributes(json);
        } catch (JsonProcessingException e) {
            throw new InvalidFiltersException("Failed to serialize filters");
        }
    }

    @Override
    public Product getProductById(Long id) {
        return this.productRepository.findById(id).orElseThrow( () -> new ProductNotFoundException("Product not found"));
    }

    @Override
    public void deleteProductById(Long id) {
        this.productRepository.findById(id).ifPresent(this.productRepository::delete);
    }

    @Override
    public Product createProduct(ProductDTO productDTO) {
        Producer producer = this.producerService.getProducerById(productDTO.producerId());

        Product product = new Product(productDTO.name(), producer, productDTO.attributes());
        return this.productRepository.save(product);
    }

    @Override
    public Product updateProduct(Long id, ProductUpdateDTO productDTO) {
        Product product = this.getProductById(id);

        if (productDTO.name() != null) {
            product.setName(productDTO.name());
        }

        if (productDTO.attributes() != null) {
            Map<String, Object> existingAttributes = product.getAttributes();

            if (existingAttributes == null) {
                existingAttributes = new HashMap<>();
                product.setAttributes(existingAttributes);
            }

            for (Map.Entry<String, Object> entry : productDTO.attributes().entrySet()) {
                if (entry.getValue() == null) {
                    existingAttributes.remove(entry.getKey());
                } else {
                    existingAttributes.put(entry.getKey(), entry.getValue());
                }
            }
        }

        return productRepository.save(product);
    }
}
