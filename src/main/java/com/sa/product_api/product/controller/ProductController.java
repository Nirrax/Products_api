package com.sa.product_api.product.controller;

import com.sa.product_api.product.dto.ProductDTO;
import com.sa.product_api.product.dto.ProductResponse;
import com.sa.product_api.product.dto.ProductUpdateDTO;
import com.sa.product_api.product.model.Product;
import com.sa.product_api.product.service.ProductService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/products")
public class ProductController {
    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("")
    public ResponseEntity<List<ProductResponse>> findAll(@RequestParam Map<String, String> filters) {
        List<Product> products = this.productService.getProducts(filters);
        return ResponseEntity.ok()
                .body(products.stream().map(ProductResponse::from).toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductResponse> findOne(@PathVariable Long id) {
        return ResponseEntity.ok()
                .body(ProductResponse.from(this.productService.getProductById(id)));
    }

    @PostMapping("")
    public ResponseEntity<ProductResponse> create(@RequestBody ProductDTO productDTO) {
        return ResponseEntity.ok()
                .body(ProductResponse.from(this.productService.createProduct(productDTO)));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<ProductResponse> update(@RequestBody ProductUpdateDTO productDTO, @PathVariable Long id) {
        return ResponseEntity.ok()
                .body(ProductResponse.from(this.productService.updateProduct(id, productDTO)));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        this.productService.deleteProductById(id);
        return ResponseEntity.noContent().build();
    }
}
