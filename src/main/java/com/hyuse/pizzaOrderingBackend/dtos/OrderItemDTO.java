package com.hyuse.pizzaOrderingBackend.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderItemDTO {
    private UUID id;
    private Long pizzaId;
    private String pizzaName;
    private Integer quantity;
    private BigDecimal price;
    private BigDecimal subtotal;
} 