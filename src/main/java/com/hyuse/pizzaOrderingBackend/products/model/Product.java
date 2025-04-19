package com.hyuse.pizzaOrderingBackend.products.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Setter
@Getter
@Entity
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String description;

    private BigDecimal price;

    public Product(String description, BigDecimal price) {
        this.description = description;
        this.price = price;
    }

    public Product(){

    }
}
