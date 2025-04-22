package com.hyuse.pizzaOrderingBackend.cart.util;

import com.hyuse.pizzaOrderingBackend.cart.dto.CartDTO;
import com.hyuse.pizzaOrderingBackend.cart.dto.CartItemDTO;
import com.hyuse.pizzaOrderingBackend.cart.model.Cart;
import com.hyuse.pizzaOrderingBackend.cart.model.CartItem;
import org.springframework.stereotype.Component;

@Component
public class CartMapper {

    public CartDTO toDTO(Cart cart) {

        return new CartDTO(
                cart.getUser().getId(),
                cart.getItems(),
                cart.getTotal()
        );
    }

    public CartItemDTO toDTO(CartItem item) {

        return new CartItemDTO(
                item.getProduct().getId(),
                item.getProduct().getProductName(),
                item.getQuantity(),
                item.getProduct().getPrice(),
                item.getSubtotal()
        );
    }

    //TODO toEntity

}
