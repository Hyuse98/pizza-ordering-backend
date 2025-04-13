package com.hyuse.pizzaOrderingBackend.controller;

import com.hyuse.pizzaOrderingBackend.assembler.PizzaAssembler;
import com.hyuse.pizzaOrderingBackend.domain.enums.PizzaFlavor;
import com.hyuse.pizzaOrderingBackend.domain.product.Pizza;
import com.hyuse.pizzaOrderingBackend.service.PizzaServiceImpl;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("/api/pizza")
public class PizzaController {

    private final PizzaServiceImpl pizzaService;
    private final PizzaAssembler pizzaAssembler;

    @Autowired
    public PizzaController(PizzaServiceImpl pizzaService, PizzaAssembler pizzaAssembler) {
        this.pizzaService = pizzaService;
        this.pizzaAssembler = pizzaAssembler;
    }

    @PostMapping
    public ResponseEntity<EntityModel<Pizza>> createPizza(@RequestBody @Valid Pizza pizza) {

        var createdPizza = pizzaService.createPizza(pizza);
        EntityModel<Pizza> entityModel = pizzaAssembler.toModel(createdPizza);
        return ResponseEntity.status(HttpStatus.CREATED).body(entityModel);
    }

    @GetMapping("/flavors/{flavor}")
    public ResponseEntity<EntityModel<Pizza>> getPizzaByFlavor(PizzaFlavor flavor) {

        var pizza = pizzaService.findPizzaByFlavor(flavor);
        EntityModel<Pizza> entityModel = pizzaAssembler.toModel(pizza);
        return ResponseEntity.ok(entityModel);
    }

    @GetMapping("/{id}")
    public ResponseEntity<EntityModel<Pizza>> getPizzaById(Long id) {

        var pizza = pizzaService.findPizzaById(id);
        EntityModel<Pizza> entityModel = pizzaAssembler.toModel(pizza);
        return ResponseEntity.ok(entityModel);
    }

    @GetMapping
    public ResponseEntity<CollectionModel<EntityModel<Pizza>>> listAllPizzas() {

        Collection<Pizza> pizzas = pizzaService.findAllPizzas();
        Collection<EntityModel<Pizza>> pizzaEntityModels = pizzas.stream()
                .map(pizzaAssembler::toModel)
                .collect(Collectors.toList());

        CollectionModel<EntityModel<Pizza>> collectionModel = CollectionModel.of(pizzaEntityModels,
                linkTo(methodOn(PizzaController.class).listAllPizzas()).withSelfRel());

        return ResponseEntity.ok(collectionModel);
    }

    //TODO Implementar metodo Atualizar Depois
    @PutMapping
    public ResponseEntity<EntityModel<Pizza>> updatePizza() {

        return null;
    }

    @DeleteMapping
    public ResponseEntity<EntityModel<Pizza>> deletePizza(Long id) {

        pizzaService.deletePizza(id);
        return ResponseEntity.noContent().build();
    }
}
