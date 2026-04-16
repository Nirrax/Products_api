package com.sa.product_api.product.service;

import com.sa.product_api.producer.model.Producer;
import com.sa.product_api.producer.service.ProducerService;
import com.sa.product_api.product.dto.ProductDTO;
import com.sa.product_api.product.dto.ProductUpdateDTO;
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

    public ProductServiceImpl(ProductRepository productRepository, ProducerService producerService) {
        this.productRepository = productRepository;
        this.producerService = producerService;
    }

    @Override
    public List<Product> getProducts() {
        return this.productRepository.findAll();
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
