package com.hyuse.pizzaOrderingBackend.order.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.UUID;

public record OrderItemDTO (
         String productName,
         BigDecimal price,
         Integer quantity,
         BigDecimal subtotal
){}