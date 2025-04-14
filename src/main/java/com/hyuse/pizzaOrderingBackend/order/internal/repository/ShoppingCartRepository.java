package com.hyuse.pizzaOrderingBackend.order.internal.repository;

import com.hyuse.pizzaOrderingBackend.order.internal.model.ShoppingCart;
import com.hyuse.pizzaOrderingBackend.user.internal.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ShoppingCartRepository extends JpaRepository<ShoppingCart, String> {
    Optional<ShoppingCart> findByUser(User user);
} 