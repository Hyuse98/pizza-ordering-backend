package com.hyuse.pizzaOrderingBackend.order.internal.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AddToCartRequest {
    private Long pizzaId;
    private Integer quantity;
} 