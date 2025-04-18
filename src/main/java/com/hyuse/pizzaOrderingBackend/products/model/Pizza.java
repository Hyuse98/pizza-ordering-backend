package com.hyuse.pizzaOrderingBackend.products.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Setter
@Getter
@Entity
public class Pizza {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String pizzaFlavor;

    private String description;

    private BigDecimal price;

    public Pizza(String pizzaFlavor, String description, BigDecimal price) {
        this.pizzaFlavor = pizzaFlavor;
        this.description = description;
        this.price = price;
    }

    public Pizza(){

    }
}
