package com.hyuse.pizzaOrderingBackend.products.service;

import com.hyuse.pizzaOrderingBackend.products.dto.PizzaDTO;
import com.hyuse.pizzaOrderingBackend.products.model.Pizza;

import java.util.Collection;

public interface PizzaServiceInterface {

    Pizza createPizza(PizzaDTO pizzaDTO);

    Pizza findPizzaById(Long id);

    Pizza findPizzaByFlavor(String pizzaFlavor);

    Collection<Pizza> findAllPizzas();

    Pizza updatePizza(Long id, PizzaDTO pizzaDTO);

    void deletePizza(Long id);
}
