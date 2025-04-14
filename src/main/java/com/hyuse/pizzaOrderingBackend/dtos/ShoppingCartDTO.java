package com.hyuse.pizzaOrderingBackend.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ShoppingCartDTO {
    private String id;
    private List<CartItemDTO> items;
    private BigDecimal total;
} 