package com.hyuse.pizzaOrderingBackend.order.api.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CartItemDTO {
    private String id;
    private Long pizzaId;
    private Integer quantity;
    private BigDecimal price;
    private BigDecimal subtotal;
} 