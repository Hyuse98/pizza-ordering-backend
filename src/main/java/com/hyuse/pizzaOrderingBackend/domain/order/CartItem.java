package com.hyuse.pizzaOrderingBackend.domain.order;

import com.hyuse.pizzaOrderingBackend.domain.product.Pizza;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CartItem {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @ManyToOne
    private Pizza pizza;

    private Integer quantity;

    private BigDecimal itemPrice;
    
    @ManyToOne
    @JoinColumn(name = "cart_id")
    private ShoppingCart cart;
    
    public BigDecimal getSubtotal() {
        return itemPrice.multiply(BigDecimal.valueOf(quantity));
    }
} 