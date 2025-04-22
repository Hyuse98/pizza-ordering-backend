package com.hyuse.pizzaOrderingBackend.cart.service.impl;

import com.hyuse.pizzaOrderingBackend.cart.dto.CartDTO;
import com.hyuse.pizzaOrderingBackend.cart.model.Cart;
import com.hyuse.pizzaOrderingBackend.cart.repository.CartRepository;
import com.hyuse.pizzaOrderingBackend.cart.service.CartServiceInterface;
import com.hyuse.pizzaOrderingBackend.cart.util.CartMapper;
import com.hyuse.pizzaOrderingBackend.products.model.Product;
import com.hyuse.pizzaOrderingBackend.products.repository.ProductRepository;
import com.hyuse.pizzaOrderingBackend.user.model.User;
import com.hyuse.pizzaOrderingBackend.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
public class CartServiceImpl implements CartServiceInterface {

    private final CartRepository cartRepository;
    private final UserRepository userRepository;
    private final ProductRepository productRepository;
    private final CartMapper cartMapper;

    @Autowired
    public CartServiceImpl(CartRepository cartRepository, ProductRepository productRepository, UserRepository userRepository, CartMapper cartMapper) {
        this.cartRepository = cartRepository;
        this.productRepository = productRepository;
        this.userRepository = userRepository;
        this.cartMapper = cartMapper;
    }

    @Transactional
    @Override
    public CartDTO addItemToCart(UUID userId, Long productId, Integer quantity) {
        Cart cart = cartRepository.findByUserId(userId);
        if (cart == null) {
            User user = userRepository.findById(userId)
                    .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

            cart = new Cart();
            cart.setUser(user);
            user.setCart(cart);
            cart = cartRepository.save(cart);
        }

        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Produto não encontrado"));

//        if (product.getStockQuantity() < quantity) {
//            throw new RuntimeException("Quantidade insuficiente em estoque");
//        }

        cart.addItem(product, quantity);
        cart = cartRepository.save(cart);

        return cartMapper.toDTO(cart);
    }

    @Transactional
    @Override
    public CartDTO removeCartItem(UUID userId, Long productId) {
        Cart cart = cartRepository.findByUserId(userId);
        if (cart == null) {
            throw new RuntimeException("Carrinho não encontrado");
        }

        cart.removeItem(productId);
        cart = cartRepository.save(cart);

        return cartMapper.toDTO(cart);
    }

    @Transactional
    @Override
    public CartDTO getCartByUserId(UUID userId) {
        Cart cart = cartRepository.findByUserId(userId);

        if (cart == null) {
            User user = userRepository.findById(userId)
                    .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

            cart = new Cart();
            cart.setUser(user);
            user.setCart(cart);
            cart = cartRepository.save(cart);
        }

        return cartMapper.toDTO(cart);
    }

    @Transactional
    @Override
    public void clearCart(UUID userId) {
        Cart cart = cartRepository.findByUserId(userId);
        if (cart != null) {
            cart.clear();
            cartRepository.save(cart);
        }
    }
}