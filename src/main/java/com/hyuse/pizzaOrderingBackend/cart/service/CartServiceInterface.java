package com.hyuse.pizzaOrderingBackend.cart.service;

import com.hyuse.pizzaOrderingBackend.cart.dto.CartDTO;

import java.util.UUID;

public interface CartServiceInterface {

    CartDTO addItemToCart(UUID userId, Long productId, Integer quantity);

    CartDTO removeCartItem(UUID userId, Long productId);

    CartDTO getCartByUserId(UUID userId);

    void clearCart(UUID userId);
}
