package com.hyuse.pizzaOrderingBackend.domain.product;

import com.hyuse.pizzaOrderingBackend.domain.enums.PizzaBorders;
import com.hyuse.pizzaOrderingBackend.domain.enums.PizzaFlavor;
import com.hyuse.pizzaOrderingBackend.domain.enums.PizzaSizes;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Collection;

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
