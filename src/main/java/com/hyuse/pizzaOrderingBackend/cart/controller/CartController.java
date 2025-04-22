package com.hyuse.pizzaOrderingBackend.cart.controller;

import com.hyuse.pizzaOrderingBackend.cart.dto.CartDTO;
import com.hyuse.pizzaOrderingBackend.cart.model.Cart;
import com.hyuse.pizzaOrderingBackend.cart.service.CartServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/cart")
public class CartController {

    private final CartServiceInterface cartService;

    @Autowired
    public CartController(CartServiceInterface cartService) {
        this.cartService = cartService;
    }

    @GetMapping("/{userId}")
    public ResponseEntity<CartDTO> getCart(@PathVariable UUID userId) {
        CartDTO cart = cartService.getCartByUserId(userId);
        return ResponseEntity.ok(cart);
    }

    @PostMapping("/{userId}/items")
    public ResponseEntity<CartDTO> addItemToCart(
            @PathVariable UUID userId,
            @RequestParam Long productId,
            @RequestParam Integer quantity) {
        CartDTO cart = cartService.addItemToCart(userId, productId, quantity);
        return ResponseEntity.ok(cart);
    }

    @DeleteMapping("/{userId}/items/{productId}")
    public ResponseEntity<CartDTO> removeItemFromCart(
            @PathVariable UUID userId,
            @PathVariable Long productId) {
        CartDTO cart = cartService.removeCartItem(userId, productId);
        return ResponseEntity.ok(cart);
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<Void> clearCart(@PathVariable UUID userId) {
        cartService.clearCart(userId);
        return ResponseEntity.ok().build();
    }
} 