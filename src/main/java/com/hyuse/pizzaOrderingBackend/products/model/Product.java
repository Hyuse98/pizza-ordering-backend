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
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "product_seq")
    @SequenceGenerator(name = "product_seq", sequenceName = "product_seq", allocationSize = 1)
    private Long id;

    private String productName;

    @Column(precision = 10, scale = 2)
    private BigDecimal price;

    public Product(String productName, BigDecimal price) {
        this.productName = productName;
        this.price = price;
    }

    public Product(){

    }
}
