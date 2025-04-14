package com.hyuse.pizzaOrderingBackend.order.internal.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderService {

    //TODO oasdw

//    private final OrderRepository orderRepository;
//    private final ShoppingCartRepository cartRepository;
//    private final UserServiceImpl userService;
//    private final ShoppingCartService cartService;
//
//    @Transactional(readOnly = true)
//    public List<OrderDTO> getUserOrders() {
//        User currentUser = userService.getCurrentUser();
//        List<Order> orders = orderRepository.findByUserOrderByOrderDateDesc(currentUser);
//        return orders.stream()
//                .map(this::convertToDTO)
//                .collect(Collectors.toList());
//    }
//
//    @Transactional(readOnly = true)
//    public OrderDTO getOrderById(UUID id) {
//        User currentUser = userService.getCurrentUser();
//        Order order = orderRepository.findById(id)
//                .orElseThrow(() -> new ResourceNotFoundException("Pedido não encontrado"));
//
//        // Verificar se o pedido pertence ao usuário atual
//        if (!order.getUser().equals(currentUser)) {
//            throw new IllegalStateException("Acesso negado: este pedido não pertence ao usuário atual");
//        }
//
//        return convertToDTO(order);
//    }
//
//    @Transactional
//    public OrderDTO createOrder(CreateOrderRequest request) {
//        User currentUser = userService.getCurrentUser();
//        ShoppingCart cart = cartRepository.findByUser(currentUser)
//                .orElseThrow(() -> new IllegalStateException("Carrinho não encontrado"));
//
//        if (cart.getItems().isEmpty()) {
//            throw new IllegalStateException("Não é possível criar um pedido com carrinho vazio");
//        }
//
//        String deliveryAddress = request.getDeliveryAddress();
//        String contactPhone = request.getContactPhone();
//
//        // Se a opção de usar o endereço do usuário estiver ativada,
//        // substitua o endereço fornecido pelo endereço cadastrado
//        if (request.isUseUserAddress()) {
//            deliveryAddress = currentUser.getAddress().toString();
//            // Se o telefone não foi fornecido, use o telefone do cadastro
//            if (contactPhone == null || contactPhone.trim().isEmpty()) {
//                contactPhone = currentUser.getPhone().getPhoneNumber();
//            }
//        }
//
//        Order order = Order.createFromCart(
//                cart,
//                deliveryAddress,
//                contactPhone,
//                request.getPaymentMethod()
//        );
//
//        order = orderRepository.save(order);
//
//        // Limpar o carrinho após criar o pedido
//        cartService.clearCart();
//
//        return convertToDTO(order);
//    }
//
//    @Transactional
//    public OrderDTO updateOrderStatus(UUID id, OrderStatus status) {
//        Order order = orderRepository.findById(id)
//                .orElseThrow(() -> new ResourceNotFoundException("Pedido não encontrado"));
//
//        order.setStatus(status);
//        order = orderRepository.save(order);
//
//        return convertToDTO(order);
//    }
//
//    @Transactional
//    public OrderDTO cancelOrder(UUID id) {
//        Order order = orderRepository.findById(id)
//                .orElseThrow(() -> new ResourceNotFoundException("Pedido não encontrado"));
//
//        // Verificar se o pedido está em um estado que pode ser cancelado
//        if (order.getStatus() == OrderStatus.DELIVERED) {
//            throw new IllegalStateException("Não é possível cancelar um pedido já entregue");
//        }
//
//        order.setStatus(OrderStatus.CANCELLED);
//        order = orderRepository.save(order);
//
//        return convertToDTO(order);
//    }
//
//    private OrderDTO convertToDTO(Order order) {
//        List<OrderItemDTO> itemDTOs = order.getItems().stream()
//                .map(this::convertToDTO)
//                .collect(Collectors.toList());
//
//        return new OrderDTO(
//                order.getId(),
//                itemDTOs,
//                order.getOrderDate(),
//                order.getStatus(),
//                order.getTotal(),
//                order.getDeliveryAddress(),
//                order.getContactPhone(),
//                order.getPaymentMethod()
//        );
//    }
//
//    private OrderItemDTO convertToDTO(OrderItem item) {
//        return new OrderItemDTO(
//                item.getId(),
//                item.getPizza() != null ? item.getPizza().getId() : null,
//                item.getPizza() != null ? item.getPizza().getPizzaFlavor().toString() : null,
//                item.getQuantity(),
//                item.getPrice(),
//                item.getSubtotal()
//        );
//    }
} 