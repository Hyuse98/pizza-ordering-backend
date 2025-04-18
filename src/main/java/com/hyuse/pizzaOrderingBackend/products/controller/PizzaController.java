package com.hyuse.pizzaOrderingBackend.products.controller;

import com.hyuse.pizzaOrderingBackend.products.dto.PizzaDTO;
import com.hyuse.pizzaOrderingBackend.products.service.PizzaServiceInterface;
import com.hyuse.pizzaOrderingBackend.products.util.PizzaAssembler;
import com.hyuse.pizzaOrderingBackend.products.model.Pizza;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
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

    private final PizzaServiceInterface pizzaService;
    private final PizzaAssembler pizzaAssembler;

    @Autowired
    public PizzaController(PizzaServiceInterface pizzaService, PizzaAssembler pizzaAssembler) {
        this.pizzaService = pizzaService;
        this.pizzaAssembler = pizzaAssembler;
    }

    @SecurityRequirement(name = "bearerAuth")
    @PostMapping
    public ResponseEntity<EntityModel<Pizza>> createPizza(@RequestBody @Valid PizzaDTO pizzaDTO) {
        var createdPizza = pizzaService.createPizza(pizzaDTO);
        EntityModel<Pizza> entityModel = pizzaAssembler.toModel(createdPizza);
        return ResponseEntity.status(HttpStatus.CREATED).body(entityModel);
    }

    @SecurityRequirement(name = "bearerAuth")
    @GetMapping("/flavors/{flavor}")
    public ResponseEntity<EntityModel<Pizza>> getPizzaByFlavor(String flavor) {

        var pizza = pizzaService.findPizzaByFlavor(flavor);
        EntityModel<Pizza> entityModel = pizzaAssembler.toModel(pizza);
        return ResponseEntity.ok(entityModel);
    }

    @SecurityRequirement(name = "bearerAuth")
    @GetMapping("/{id}")
    public ResponseEntity<EntityModel<Pizza>> getPizzaById(Long id) {

        var pizza = pizzaService.findPizzaById(id);
        EntityModel<Pizza> entityModel = pizzaAssembler.toModel(pizza);
        return ResponseEntity.ok(entityModel);
    }

    @SecurityRequirement(name = "bearerAuth")
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

    @SecurityRequirement(name = "bearerAuth")
    @PutMapping
    public ResponseEntity<EntityModel<Pizza>> updatePizza(@PathVariable Long id,@RequestBody @Valid PizzaDTO pizzaDTO) {
        var pizza = pizzaService.updatePizza(id, pizzaDTO);
        EntityModel<Pizza> entityModel = pizzaAssembler.toModel(pizza);
        return ResponseEntity.ok(entityModel);
    }

    @SecurityRequirement(name = "bearerAuth")
    @DeleteMapping
    public ResponseEntity<EntityModel<Pizza>> deletePizza(Long id) {

        pizzaService.deletePizza(id);
        return ResponseEntity.noContent().build();
    }
}
