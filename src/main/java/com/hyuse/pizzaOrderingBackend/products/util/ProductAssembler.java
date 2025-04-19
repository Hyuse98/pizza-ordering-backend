package com.hyuse.pizzaOrderingBackend.products.util;

import com.hyuse.pizzaOrderingBackend.products.controller.ProductController;
import com.hyuse.pizzaOrderingBackend.products.model.Product;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class ProductAssembler implements RepresentationModelAssembler<Product, EntityModel<Product>> {

    //TODO FIX
    @Override
    public EntityModel<Product> toModel(Product product) {
        return EntityModel.of(product,
                //linkTo(methodOn(ProductController.class).getPizzaByFlavor(product.getPizzaFlavor())).withSelfRel(),
                linkTo(methodOn(ProductController.class).listAllPizzas()).withRel("allPizzas"));
    }
}
