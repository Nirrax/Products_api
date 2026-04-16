package com.sa.product_api.producer.dto;

import com.sa.product_api.producer.model.Producer;

public record ProducerResponse(Long id, String name) {
    public static ProducerResponse from(Producer producer) {
        return new ProducerResponse(producer.getId(), producer.getName());
    }
}
