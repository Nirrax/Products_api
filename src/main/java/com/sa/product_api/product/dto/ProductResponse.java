package com.sa.product_api.product.dto;

import com.sa.product_api.producer.dto.ProducerResponse;
import com.sa.product_api.producer.model.Producer;
import com.sa.product_api.product.model.Product;

import java.util.Map;

public record ProductResponse(Long id, String name, Map<String, Object> attributes, ProducerResponse producer) {
    public static ProductResponse from(Product product) {
        return new ProductResponse(product.getId(), product.getName(), product.getAttributes(), ProducerResponse.from(product.getProducer()));
    }
}
