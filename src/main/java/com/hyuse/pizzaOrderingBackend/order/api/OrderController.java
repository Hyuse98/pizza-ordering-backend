package com.hyuse.pizzaOrderingBackend.order.api;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/orders")
@RequiredArgsConstructor
public class OrderController {

    //TODO asdwads

//    private final OrderService orderService;
//
//    @GetMapping
//    public ResponseEntity<List<OrderDTO>> getUserOrders() {
//        return ResponseEntity.ok(orderService.getUserOrders());
//    }
//
//    @GetMapping("/{id}")
//    public ResponseEntity<OrderDTO> getOrder(@PathVariable UUID id) {
//        return ResponseEntity.ok(orderService.getOrderById(id));
//    }
//
//    @PostMapping
//    public ResponseEntity<OrderDTO> createOrder(@RequestBody CreateOrderRequest request) {
//        // Validar se o endereço foi fornecido quando não está usando o endereço do usuário
//        if (!request.isUseUserAddress() && (request.getDeliveryAddress() == null || request.getDeliveryAddress().trim().isEmpty())) {
//            throw new IllegalArgumentException("Endereço de entrega é obrigatório quando não está usando o endereço cadastrado");
//        }
//
//        // Validar método de pagamento
//        if (request.getPaymentMethod() == null || request.getPaymentMethod().trim().isEmpty()) {
//            throw new IllegalArgumentException("Método de pagamento é obrigatório");
//        }
//
//        return ResponseEntity.ok(orderService.createOrder(request));
//    }
//
//    @PutMapping("/{id}/status")
//    public ResponseEntity<OrderDTO> updateOrderStatus(
//            @PathVariable UUID id,
//            @RequestParam OrderStatus status) {
//        return ResponseEntity.ok(orderService.updateOrderStatus(id, status));
//    }
//
//    @DeleteMapping("/{id}/cancel")
//    public ResponseEntity<OrderDTO> cancelOrder(@PathVariable UUID id) {
//        return ResponseEntity.ok(orderService.cancelOrder(id));
//    }
} 