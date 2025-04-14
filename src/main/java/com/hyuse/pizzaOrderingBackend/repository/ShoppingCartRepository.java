package com.hyuse.pizzaOrderingBackend.repository;

import com.hyuse.pizzaOrderingBackend.domain.order.ShoppingCart;
import com.hyuse.pizzaOrderingBackend.domain.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ShoppingCartRepository extends JpaRepository<ShoppingCart, String> {
    Optional<ShoppingCart> findByUser(User user);
} 