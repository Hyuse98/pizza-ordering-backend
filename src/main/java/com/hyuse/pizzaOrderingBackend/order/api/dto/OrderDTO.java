package com.hyuse.pizzaOrderingBackend.order.api.dto;

import com.hyuse.pizzaOrderingBackend.order.internal.model.OrderStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderDTO {
    private UUID id;
    private List<OrderItemDTO> items;
    private LocalDateTime orderDate;
    private OrderStatus status;
    private BigDecimal total;
    private String deliveryAddress;
    private String contactPhone;
    private String paymentMethod;
} 