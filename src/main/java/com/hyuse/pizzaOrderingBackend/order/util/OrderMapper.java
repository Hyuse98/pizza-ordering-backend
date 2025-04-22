package com.hyuse.pizzaOrderingBackend.order.util;

import com.hyuse.pizzaOrderingBackend.order.dto.OrderDTO;
import com.hyuse.pizzaOrderingBackend.order.dto.OrderItemDTO;
import com.hyuse.pizzaOrderingBackend.order.model.Order;
import com.hyuse.pizzaOrderingBackend.order.model.OrderItem;
import org.springframework.stereotype.Component;

@Component
public class OrderMapper {

    public OrderDTO toDTO(Order order) {

        return new OrderDTO(
                order.getOrderNumber(),
                order.getOrderDate(),
                order.getStatus(),
                order.getTotalAmount(),
                order.getDeliveryAddress(),
                order.getId(),
                order.getItems()
        );
    }

    public OrderItemDTO toDTO(OrderItem item) {

        return new OrderItemDTO(
                item.getDescription(),
                item.getPrice(),
                item.getQuantity(),
                item.getSubtotal()
        );
    }
}
