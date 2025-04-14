package com.hyuse.pizzaOrderingBackend.repository;

import com.hyuse.pizzaOrderingBackend.domain.order.Order;
import com.hyuse.pizzaOrderingBackend.domain.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface OrderRepository extends JpaRepository<Order, UUID> {
    List<Order> findByUserOrderByOrderDateDesc(User user);
} 