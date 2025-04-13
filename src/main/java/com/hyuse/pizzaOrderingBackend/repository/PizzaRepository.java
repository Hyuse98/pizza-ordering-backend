package com.hyuse.pizzaOrderingBackend.repository;

import com.hyuse.pizzaOrderingBackend.domain.enums.PizzaFlavor;
import com.hyuse.pizzaOrderingBackend.domain.product.Pizza;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PizzaRepository extends JpaRepository<Pizza, Long> {

    Optional<Pizza> findByPizzaFlavor(PizzaFlavor pizzaFlavor);
}