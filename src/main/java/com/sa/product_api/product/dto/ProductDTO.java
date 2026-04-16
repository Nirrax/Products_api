package com.sa.product_api.product.dto;

import jakarta.validation.constraints.NotBlank;

import java.util.Map;

public record ProductDTO(@NotBlank(message = "Product name is required") String name,
                         @NotBlank(message = "Producer ID is required") Long producerId,
                         @NotBlank(message = "Attributes are required") Map<String, Object> attributes) {
}
