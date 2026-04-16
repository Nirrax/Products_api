package com.sa.product_api.product.service;

import com.sa.product_api.product.dto.ProductDTO;
import com.sa.product_api.product.dto.ProductUpdateDTO;
import com.sa.product_api.product.model.Product;

import java.util.List;
import java.util.Map;

public interface ProductService {
    List<Product> getProducts(Map<String, String> filters);
    Product getProductById(Long id);
    void deleteProductById(Long id);
    Product createProduct(ProductDTO productDTO);
    Product updateProduct(Long id, ProductUpdateDTO productDTO);
}
