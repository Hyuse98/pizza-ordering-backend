package com.hyuse.pizzaOrderingBackend.products.repository;

import com.hyuse.pizzaOrderingBackend.products.model.Pizza;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PizzaRepository extends JpaRepository<Pizza, Long> {

    Optional<Pizza> findByPizzaFlavor(String pizzaFlavor);
}