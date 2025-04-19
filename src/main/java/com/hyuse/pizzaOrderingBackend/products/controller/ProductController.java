package com.hyuse.pizzaOrderingBackend.products.controller;

import com.hyuse.pizzaOrderingBackend.products.dto.ProductDTO;
import com.hyuse.pizzaOrderingBackend.products.model.Product;
import com.hyuse.pizzaOrderingBackend.products.service.ProductServiceInterface;
import com.hyuse.pizzaOrderingBackend.products.util.ProductAssembler;
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
public class ProductController {

    private final ProductServiceInterface productService;
    private final ProductAssembler productAssembler;

    @Autowired
    public ProductController(ProductServiceInterface productService, ProductAssembler productAssembler) {
        this.productService = productService;
        this.productAssembler = productAssembler;
    }

    @SecurityRequirement(name = "bearerAuth")
    @PostMapping
    public ResponseEntity<EntityModel<Product>> createPizza(@RequestBody @Valid ProductDTO productDTO) {
        var createdPizza = productService.createProduct(productDTO);
        EntityModel<Product> entityModel = productAssembler.toModel(createdPizza);
        return ResponseEntity.status(HttpStatus.CREATED).body(entityModel);
    }

    @SecurityRequirement(name = "bearerAuth")
    @GetMapping("/{id}")
    public ResponseEntity<EntityModel<Product>> getPizzaById(Long id) {

        var pizza = productService.findProductById(id);
        EntityModel<Product> entityModel = productAssembler.toModel(pizza);
        return ResponseEntity.ok(entityModel);
    }

    @SecurityRequirement(name = "bearerAuth")
    @GetMapping
    public ResponseEntity<CollectionModel<EntityModel<Product>>> listAllPizzas() {

        Collection<Product> products = productService.findAllProducts();
        Collection<EntityModel<Product>> pizzaEntityModels = products.stream()
                .map(productAssembler::toModel)
                .collect(Collectors.toList());

        CollectionModel<EntityModel<Product>> collectionModel = CollectionModel.of(pizzaEntityModels,
                linkTo(methodOn(ProductController.class).listAllPizzas()).withSelfRel());

        return ResponseEntity.ok(collectionModel);
    }

    @SecurityRequirement(name = "bearerAuth")
    @PutMapping
    public ResponseEntity<EntityModel<Product>> updatePizza(@PathVariable Long id, @RequestBody @Valid ProductDTO productDTO) {
        var pizza = productService.updateProduct(id, productDTO);
        EntityModel<Product> entityModel = productAssembler.toModel(pizza);
        return ResponseEntity.ok(entityModel);
    }

    @SecurityRequirement(name = "bearerAuth")
    @DeleteMapping
    public ResponseEntity<EntityModel<Product>> deletePizza(Long id) {

        productService.deleteProduct(id);
        return ResponseEntity.noContent().build();
    }
}
