package com.hyuse.pizzaOrderingBackend.products.internal.repository;

import com.hyuse.pizzaOrderingBackend.products.internal.model.PizzaFlavor;
import com.hyuse.pizzaOrderingBackend.products.internal.model.Pizza;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PizzaRepository extends JpaRepository<Pizza, Long> {

    Optional<Pizza> findByPizzaFlavor(PizzaFlavor pizzaFlavor);
}