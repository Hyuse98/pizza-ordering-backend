package com.hyuse.pizzaOrderingBackend.cart.dto;

import com.hyuse.pizzaOrderingBackend.cart.model.CartItem;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

public record CartDTO(
        UUID userId,
        List<CartItem> items,
        BigDecimal total
) {}