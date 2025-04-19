package com.hyuse.pizzaOrderingBackend.products.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;

public record ProductDTO(

        @NotBlank
        String description,

        @NotNull
        @Positive
        BigDecimal price
) {
}
