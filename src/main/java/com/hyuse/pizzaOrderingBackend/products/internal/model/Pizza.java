package com.hyuse.pizzaOrderingBackend.products.internal.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Setter
@Getter
@Entity
public class Pizza {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Enumerated(EnumType.STRING)
    private PizzaFlavor pizzaFlavor;

    private String description;

    private BigDecimal price;
}
