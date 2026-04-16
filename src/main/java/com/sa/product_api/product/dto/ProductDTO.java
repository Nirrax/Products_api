package com.sa.product_api.product.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.util.Map;

public record ProductDTO(@NotBlank(message = "Product name is required") String name,
                         @NotNull(message = "Producer ID is required") Long producerId,
                         @NotEmpty(message = "Attributes are required") Map<String, Object> attributes) {
}
