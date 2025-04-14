package com.hyuse.pizzaOrderingBackend.products.api;

import com.hyuse.pizzaOrderingBackend.products.internal.util.PizzaAssembler;
import com.hyuse.pizzaOrderingBackend.products.internal.model.PizzaFlavor;
import com.hyuse.pizzaOrderingBackend.products.internal.model.Pizza;
import com.hyuse.pizzaOrderingBackend.products.internal.service.PizzaServiceImpl;
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

    private final PizzaServiceImpl pizzaService;
    private final PizzaAssembler pizzaAssembler;

    @Autowired
    public PizzaController(PizzaServiceImpl pizzaService, PizzaAssembler pizzaAssembler) {
        this.pizzaService = pizzaService;
        this.pizzaAssembler = pizzaAssembler;
    }

    @SecurityRequirement(name = "bearerAuth")
    @PostMapping
    public ResponseEntity<EntityModel<Pizza>> createPizza(@RequestBody @Valid Pizza pizza) {

        var createdPizza = pizzaService.createPizza(pizza);
        EntityModel<Pizza> entityModel = pizzaAssembler.toModel(createdPizza);
        return ResponseEntity.status(HttpStatus.CREATED).body(entityModel);
    }

    @SecurityRequirement(name = "bearerAuth")
    @GetMapping("/flavors/{flavor}")
    public ResponseEntity<EntityModel<Pizza>> getPizzaByFlavor(PizzaFlavor flavor) {

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

    //TODO Implementar metodo Atualizar Depois
    @SecurityRequirement(name = "bearerAuth")
    @PutMapping
    public ResponseEntity<EntityModel<Pizza>> updatePizza() {

        return null;
    }

    @SecurityRequirement(name = "bearerAuth")
    @DeleteMapping
    public ResponseEntity<EntityModel<Pizza>> deletePizza(Long id) {

        pizzaService.deletePizza(id);
        return ResponseEntity.noContent().build();
    }
}
