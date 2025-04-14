package com.hyuse.pizzaOrderingBackend.order.internal.repository;

import com.hyuse.pizzaOrderingBackend.order.internal.model.Order;
import com.hyuse.pizzaOrderingBackend.user.internal.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface OrderRepository extends JpaRepository<Order, UUID> {
    List<Order> findByUserOrderByOrderDateDesc(User user);
} 