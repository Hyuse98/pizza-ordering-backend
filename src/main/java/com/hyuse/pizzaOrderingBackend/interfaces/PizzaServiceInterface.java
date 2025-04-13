package com.hyuse.pizzaOrderingBackend.interfaces;

import com.hyuse.pizzaOrderingBackend.domain.enums.PizzaFlavor;
import com.hyuse.pizzaOrderingBackend.domain.product.Pizza;

import java.util.Collection;

public interface PizzaServiceInterface {

    Pizza createPizza(Pizza pizza);

    Pizza findPizzaById(Long id);

    Pizza findPizzaByFlavor(PizzaFlavor pizzaFlavor);

    Collection<Pizza> findAllPizzas();

    Pizza updatePizza(Long id, Pizza pizza);

    void deletePizza(Long id);
}
