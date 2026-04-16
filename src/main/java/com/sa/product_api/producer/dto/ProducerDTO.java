package com.sa.product_api.producer.dto;

import jakarta.validation.constraints.NotBlank;

public record ProducerDTO(@NotBlank(message = "Producer name is required") String name) {
}
