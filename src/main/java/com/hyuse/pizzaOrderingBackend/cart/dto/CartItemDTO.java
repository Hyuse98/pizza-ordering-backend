package com.hyuse.pizzaOrderingBackend.cart.dto;

import java.math.BigDecimal;

public record CartItemDTO(
        Long id,
        String description,
        Integer quantity,
        BigDecimal price,
        BigDecimal subtotal
) {}