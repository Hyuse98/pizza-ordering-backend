package com.hyuse.pizzaOrderingBackend.order.repository;

import com.hyuse.pizzaOrderingBackend.order.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    List<Order> findByUserId(UUID userId);
    Order findByOrderNumber(String orderNumber);
}