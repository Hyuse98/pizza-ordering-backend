package com.hyuse.pizzaOrderingBackend.products.util;

import com.hyuse.pizzaOrderingBackend.products.controller.PizzaController;
import com.hyuse.pizzaOrderingBackend.products.model.Pizza;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class PizzaAssembler implements RepresentationModelAssembler<Pizza, EntityModel<Pizza>> {

    @Override
    public EntityModel<Pizza> toModel(Pizza pizza) {
        return EntityModel.of(pizza,
                linkTo(methodOn(PizzaController.class).getPizzaByFlavor(pizza.getPizzaFlavor())).withSelfRel(),
                linkTo(methodOn(PizzaController.class).listAllPizzas()).withRel("allPizzas"));
    }
}
