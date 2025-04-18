package com.hyuse.pizzaOrderingBackend.products.util;

import com.hyuse.pizzaOrderingBackend.products.dto.PizzaDTO;
import com.hyuse.pizzaOrderingBackend.products.model.Pizza;

public class PizzaMapper {

    public Pizza toEntity(PizzaDTO pizzaDTO){

        return new Pizza(
                pizzaDTO.pizzaFlavor(),
                pizzaDTO.description(),
                pizzaDTO.price()
        );
    }

    public PizzaDTO toDto(Pizza pizza){

        return new PizzaDTO(
                pizza.getPizzaFlavor(),
                pizza.getDescription(),
                pizza.getPrice()
        );
    }
}
