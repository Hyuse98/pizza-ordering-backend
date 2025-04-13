package com.hyuse.pizzaOrderingBackend.assembler;

import com.hyuse.pizzaOrderingBackend.controller.PizzaController;
import com.hyuse.pizzaOrderingBackend.domain.product.Pizza;
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
