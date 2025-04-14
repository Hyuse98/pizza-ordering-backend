package com.hyuse.pizzaOrderingBackend.order.internal.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ShoppingCartService {


//TODO sads

//    private final ShoppingCartRepository cartRepository;
//    private final UserServiceImpl userService;
//    private final PizzaService pizzaService;
//
//
//    @Transactional
//    public ShoppingCartDTO getOrCreateCart() {
//        User currentUser = userService.getCurrentUser();
//        ShoppingCart cart = cartRepository.findByUser(currentUser)
//                .orElseGet(() -> {
//                    ShoppingCart newCart = new ShoppingCart();
//                    newCart.setUser(currentUser);
//                    return cartRepository.save(newCart);
//                });
//
//        return convertToDTO(cart);
//    }
//
//    @Transactional
//    public ShoppingCartDTO addToCart(AddToCartRequest request) {
//        // Validação de quantidade
//        if (request.getQuantity() == null || request.getQuantity() <= 0) {
//            throw new IllegalArgumentException("A quantidade deve ser maior que zero");
//        }
//
//        ShoppingCart cart = getCart();
//
//        Pizza pizza = null;
//
//        if (request.getPizzaId() != null) {
//            pizza = pizzaService.getPizzaById(request.getPizzaId());
//        }
//
//        if (pizza == null) {
//            throw new IllegalArgumentException("Pedido deve conter pelo menos uma pizza");
//        }
//
//        // Verificar se já existe o mesmo item no carrinho
//        boolean itemExists = false;
//        for (CartItem existingItem : cart.getItems()) {
//            if (existingItem.getPizza() != null &&
//                existingItem.getPizza().getId().equals(pizza.getId())) {
//                // Item encontrado, atualizar quantidade
//                existingItem.setQuantity(existingItem.getQuantity() + request.getQuantity());
//                itemExists = true;
//                break;
//            }
//        }
//
//        // Se não existir, criar novo item
//        if (!itemExists) {
//            CartItem item = new CartItem();
//            item.setPizza(pizza);
//            item.setQuantity(request.getQuantity());
//            item.setItemPrice(pizza.getPrice());
//            cart.addItem(item);
//        }
//
//        cart = cartRepository.save(cart);
//
//        return convertToDTO(cart);
//    }
//
//    @Transactional
//    public ShoppingCartDTO updateCartItemQuantity(String itemId, int quantity) {
//        if (quantity <= 0) {
//            throw new IllegalArgumentException("A quantidade deve ser maior que zero");
//        }
//
//        ShoppingCart cart = getCart();
//
//        // Encontrar o item no carrinho
//        CartItem itemToUpdate = cart.getItems().stream()
//                .filter(item -> item.getId().equals(itemId))
//                .findFirst()
//                .orElseThrow(() -> new ResourceNotFoundException("Item não encontrado no carrinho"));
//
//        // Atualizar a quantidade
//        itemToUpdate.setQuantity(quantity);
//
//        cart = cartRepository.save(cart);
//        return convertToDTO(cart);
//    }
//
//    @Transactional
//    public ShoppingCartDTO removeFromCart(String itemId) {
//        ShoppingCart cart = getCart();
//
//        cart.getItems().stream()
//                .filter(item -> item.getId().equals(itemId))
//                .findFirst()
//                .ifPresent(cart::removeItem);
//
//        cart = cartRepository.save(cart);
//        return convertToDTO(cart);
//    }
//
//    @Transactional
//    public ShoppingCartDTO clearCart() {
//        ShoppingCart cart = getCart();
//        cart.clear();
//        cart = cartRepository.save(cart);
//        return convertToDTO(cart);
//    }
//
//    private ShoppingCart getCart() {
//        User currentUser = userService.getCurrentUser();
//        return cartRepository.findByUser(currentUser)
//                .orElseGet(() -> {
//                    ShoppingCart newCart = new ShoppingCart();
//                    newCart.setUser(currentUser);
//                    return cartRepository.save(newCart);
//                });
//    }
//
//    private ShoppingCartDTO convertToDTO(ShoppingCart cart) {
//        List<CartItemDTO> itemDTOs = cart.getItems().stream()
//                .map(this::convertToDTO)
//                .collect(Collectors.toList());
//
//        return new ShoppingCartDTO(
//                cart.getId(),
//                itemDTOs,
//                cart.getTotal()
//        );
//    }
//
//    private CartItemDTO convertToDTO(CartItem item) {
//        return new CartItemDTO(
//                item.getId(),
//                item.getPizza() != null ? item.getPizza().getId() : null,
//                item.getQuantity(),
//                item.getItemPrice(),
//                item.getSubtotal()
//        );
//    }
} 