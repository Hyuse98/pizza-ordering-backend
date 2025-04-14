package com.hyuse.pizzaOrderingBackend.domain.order;

import com.hyuse.pizzaOrderingBackend.domain.enums.OrderStatus;
import com.hyuse.pizzaOrderingBackend.domain.user.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "orders")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne
    private User user;
    
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "order_id")
    private List<OrderItem> items = new ArrayList<>();
    
    private LocalDateTime orderDate;
    
    @Enumerated(EnumType.STRING)
    private OrderStatus status;
    
    private BigDecimal total;
    
    private String deliveryAddress;
    
    private String contactPhone;
    
    private String paymentMethod;
    
    public static Order createFromCart(ShoppingCart cart, String deliveryAddress, String contactPhone, String paymentMethod) {
        Order order = new Order();
        order.setUser(cart.getUser());
        order.setOrderDate(LocalDateTime.now());
        order.setStatus(OrderStatus.PENDING);
        order.setTotal(cart.getTotal());
        order.setDeliveryAddress(deliveryAddress);
        order.setContactPhone(contactPhone);
        order.setPaymentMethod(paymentMethod);
        
        // Converter itens do carrinho para itens do pedido
        cart.getItems().forEach(cartItem -> {
            OrderItem orderItem = new OrderItem();
            orderItem.setPizza(cartItem.getPizza());
            orderItem.setQuantity(cartItem.getQuantity());
            orderItem.setPrice(cartItem.getItemPrice());
            order.getItems().add(orderItem);
        });
        
        return order;
    }
}
