package com.hyuse.pizzaOrderingBackend.products.internal.service;

import com.hyuse.pizzaOrderingBackend.products.internal.model.PizzaFlavor;
import com.hyuse.pizzaOrderingBackend.products.internal.model.Pizza;

import java.util.Collection;

public interface PizzaServiceInterface {

    Pizza createPizza(Pizza pizza);

    Pizza findPizzaById(Long id);

    Pizza findPizzaByFlavor(PizzaFlavor pizzaFlavor);

    Collection<Pizza> findAllPizzas();

    Pizza updatePizza(Long id, Pizza pizza);

    void deletePizza(Long id);
}
