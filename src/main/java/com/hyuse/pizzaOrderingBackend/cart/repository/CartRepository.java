package com.hyuse.pizzaOrderingBackend.cart.repository;

import com.hyuse.pizzaOrderingBackend.cart.model.Cart;
import com.hyuse.pizzaOrderingBackend.user.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface CartRepository extends JpaRepository<Cart, String> {

    Optional<Cart> findByUser(User user);
    Cart findByUserId(UUID userId);
}