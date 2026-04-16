package com.sa.product_api.product.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.Map;

@JsonInclude(JsonInclude.Include.ALWAYS)
public record ProductUpdateDTO(String name, Map<String, Object> attributes) {
}
