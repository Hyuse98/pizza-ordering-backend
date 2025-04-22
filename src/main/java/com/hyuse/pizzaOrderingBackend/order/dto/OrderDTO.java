package com.hyuse.pizzaOrderingBackend.order.dto;

import com.hyuse.pizzaOrderingBackend.order.model.OrderItem;
import com.hyuse.pizzaOrderingBackend.order.model.enums.OrderStatus;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public record OrderDTO(
        String orderNumber,
        LocalDateTime orderDate,
        OrderStatus status,
        BigDecimal totalAmount,
        String shippingAddress,
        UUID userId,
        List<OrderItem> items
) {
} 