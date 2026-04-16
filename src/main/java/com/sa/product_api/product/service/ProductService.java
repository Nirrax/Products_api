package com.sa.product_api.product.service;

import com.sa.product_api.product.dto.ProductDTO;
import com.sa.product_api.product.dto.ProductUpdateDTO;
import com.sa.product_api.product.model.Product;

import java.util.List;

public interface ProductService {
    List<Product> getProducts();
    Product getProductById(Long id);
    void deleteProductById(Long id);
    Product createProduct(ProductDTO productDTO);
    Product updateProduct(Long id, ProductUpdateDTO productDTO);
}
