package com.hyuse.pizzaOrderingBackend.controller;

import com.hyuse.pizzaOrderingBackend.dtos.ShoppingCartDTO;
import com.hyuse.pizzaOrderingBackend.dtos.requests.AddToCartRequest;
import com.hyuse.pizzaOrderingBackend.service.ShoppingCartService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/cart")
@RequiredArgsConstructor
public class CartController {

    private final ShoppingCartService cartService;

    @GetMapping
    public ResponseEntity<ShoppingCartDTO> getCart() {
        return ResponseEntity.ok(cartService.getOrCreateCart());
    }

    @PostMapping("/add")
    public ResponseEntity<ShoppingCartDTO> addToCart(@RequestBody AddToCartRequest request) {
        if (request.getQuantity() == null || request.getQuantity() <= 0) {
            throw new IllegalArgumentException("A quantidade deve ser maior que zero");
        }
        
        if (request.getPizzaId() == null) {
            throw new IllegalArgumentException("ID da pizza é obrigatório");
        }
        
        return ResponseEntity.ok(cartService.addToCart(request));
    }
    
    @PutMapping("/items/{itemId}/quantity/{quantity}")
    public ResponseEntity<ShoppingCartDTO> updateCartItemQuantity(
            @PathVariable String itemId,
            @PathVariable int quantity) {
        if (quantity <= 0) {
            throw new IllegalArgumentException("A quantidade deve ser maior que zero");
        }
        
        return ResponseEntity.ok(cartService.updateCartItemQuantity(itemId, quantity));
    }
    
    @DeleteMapping("/items/{itemId}")
    public ResponseEntity<ShoppingCartDTO> removeFromCart(@PathVariable String itemId) {
        return ResponseEntity.ok(cartService.removeFromCart(itemId));
    }
    
    @DeleteMapping("/clear")
    public ResponseEntity<ShoppingCartDTO> clearCart() {
        return ResponseEntity.ok(cartService.clearCart());
    }
} 