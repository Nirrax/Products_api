package com.sa.product_api.product.dto;

import java.util.Map;

public record ProductUpdateDTO(String name, Map<String, Object> attributes) {
}
