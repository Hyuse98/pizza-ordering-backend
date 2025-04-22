package com.hyuse.pizzaOrderingBackend.order.service.impl;

import com.hyuse.pizzaOrderingBackend.order.dto.OrderDTO;
import com.hyuse.pizzaOrderingBackend.cart.model.CartItem;
import com.hyuse.pizzaOrderingBackend.order.model.Order;
import com.hyuse.pizzaOrderingBackend.order.model.OrderItem;
import com.hyuse.pizzaOrderingBackend.cart.model.Cart;
import com.hyuse.pizzaOrderingBackend.order.model.enums.OrderStatus;
import com.hyuse.pizzaOrderingBackend.order.repository.OrderRepository;
import com.hyuse.pizzaOrderingBackend.cart.repository.CartRepository;
import com.hyuse.pizzaOrderingBackend.order.util.OrderMapper;
import com.hyuse.pizzaOrderingBackend.user.model.User;
import com.hyuse.pizzaOrderingBackend.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class OrderServiceImpl {

    private final OrderRepository orderRepository;
    private final UserRepository userRepository;
    private final CartRepository cartRepository;
    private final OrderMapper orderMapper;

    @Autowired
    public OrderServiceImpl(OrderRepository orderRepository,
                            UserRepository userRepository,
                            CartRepository cartRepository,
                            OrderMapper orderMapper){
        this.cartRepository = cartRepository;
        this.orderRepository = orderRepository;
        this.userRepository = userRepository;
        this.orderMapper = orderMapper;
    }

    @Transactional
    public OrderDTO createOrderFromCart(UUID userId, String shippingAddress) {

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        Cart cart = cartRepository.findByUserId(userId);
        if (cart == null || cart.getItems().isEmpty()) {
            throw new RuntimeException("Carrinho vazio ou não encontrado");
        }

//        // Verificar estoque
//        for (CartItem item : cart.getItems()) {
//            Product product = item.getProduct();
//            if (product.getStockQuantity() < item.getQuantity()) {
//                throw new RuntimeException("Produto " + product.getName() + " não possui estoque suficiente");
//            }
//
//            // Atualizar estoque
//            product.setStockQuantity(product.getStockQuantity() - item.getQuantity());
//            productRepository.save(product);
//        }

        // Criar pedido
        Order order = new Order();
        order.setOrderNumber(generateOrderNumber());
        order.setOrderDate(LocalDateTime.now());
        order.setStatus(OrderStatus.PENDING);
        order.setDeliveryAddress(shippingAddress);
        order.setUser(user);

        // Adicionar itens ao pedido
        for (CartItem cartItem : cart.getItems()) {
            OrderItem orderItem = new OrderItem();
            orderItem.setDescription(cartItem.getProduct().getProductName());
            orderItem.setPrice(cartItem.getProduct().getPrice());
            orderItem.setQuantity(cartItem.getQuantity());

            order.addItem(orderItem);
        }

        order.setTotalAmount(order.calculateTotal());
        order = orderRepository.save(order);

        // Limpar carrinho após finalizar pedido
        cart.clear();
        cartRepository.save(cart);

        return orderMapper.toDTO(order);
    }

    @Transactional(readOnly = true)
    public OrderDTO getOrderByNumber(String orderNumber) {
        Order order = orderRepository.findByOrderNumber(orderNumber);
        if (order == null) {
            throw new RuntimeException("Pedido não encontrado");
        }
        return orderMapper.toDTO(order);
    }

    @Transactional(readOnly = true)
    public List<OrderDTO> getOrdersByUserId(UUID userId) {
        List<Order> orders = orderRepository.findByUserId(userId);
        return orders.stream()
                .map(orderMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Transactional
    public OrderDTO updateOrderStatus(String orderNumber, OrderStatus status) {

        Order order = orderRepository.findByOrderNumber(orderNumber);
        if (order == null) {
            throw new RuntimeException("Pedido não encontrado");
        }

        order.setStatus(status);
        order = orderRepository.save(order);

        return orderMapper.toDTO(order);
    }

    private String generateOrderNumber() {
        return "ORD-" + UUID.randomUUID().toString().substring(0, 8).toUpperCase();
    }


}