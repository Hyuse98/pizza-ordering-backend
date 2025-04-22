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
@RequestMapping("/api/product")
public class ProductController {

    private final ProductServiceInterface productService;
    private final ProductAssembler productAssembler;

    @Autowired
    public ProductController(ProductServiceInterface productService, ProductAssembler productAssembler) {
        this.productService = productService;
        this.productAssembler = productAssembler;
    }

    //TODO ERROR "status": 500, "message": "Ocorreu um erro interno no servidor: could not execute statement [Unique index or primary key violation: \"PRIMARY KEY ON PUBLIC.PRODUCT(ID) ( /* key:1 */ 2500.00, CAST(1 AS BIGINT), 'Notebook')\"; SQL statement:\ninsert into product (price,product_name,id) values (?,?,?) [23505-232]] [insert into product (price,product_name,id) values (?,?,?)]; SQL [insert into product (price,product_name,id) values (?,?,?)]; constraint [PRIMARY KEY]",
    @SecurityRequirement(name = "bearerAuth")
    @PostMapping
    public ResponseEntity<EntityModel<Product>> createProduct(@RequestBody @Valid ProductDTO productDTO) {
        var createdProduct = productService.createProduct(productDTO);
        EntityModel<Product> entityModel = productAssembler.toModel(createdProduct);
        return ResponseEntity.status(HttpStatus.CREATED).body(entityModel);
    }

    //TODO "status": 500,
    //    "message": "Ocorreu um erro interno no servidor: The given id must not be null",
    @SecurityRequirement(name = "bearerAuth")
    @GetMapping("/{id}")
    public ResponseEntity<EntityModel<Product>> getProductById(Long id) {

        var product = productService.findProductById(id);
        EntityModel<Product> entityModel = productAssembler.toModel(product);
        return ResponseEntity.ok(entityModel);
    }

    @SecurityRequirement(name = "bearerAuth")
    @GetMapping
    public ResponseEntity<CollectionModel<EntityModel<Product>>> listAllProducts() {

        Collection<Product> products = productService.findAllProducts();
        Collection<EntityModel<Product>> productEntityModels = products.stream()
                .map(productAssembler::toModel)
                .collect(Collectors.toList());

        CollectionModel<EntityModel<Product>> collectionModel = CollectionModel.of(productEntityModels,
                linkTo(methodOn(ProductController.class).listAllProducts()).withSelfRel());

        return ResponseEntity.ok(collectionModel);
    }

    @SecurityRequirement(name = "bearerAuth")
    @PutMapping("/{id}")
    public ResponseEntity<EntityModel<Product>> updateProduct(@PathVariable Long id, @RequestBody @Valid ProductDTO productDTO) {
        var product = productService.updateProduct(id, productDTO);
        EntityModel<Product> entityModel = productAssembler.toModel(product);
        return ResponseEntity.ok(entityModel);
    }

    //TODO "status": 500, "message": "Ocorreu um erro interno no servidor: could not execute statement [Referential integrity constraint violation: \"FK_CART_ITEM_PRODUCT: PUBLIC.CART_ITEM FOREIGN KEY(PRODUCT_ID) REFERENCES PUBLIC.PRODUCT(ID) (CAST(10 AS BIGINT))\"; SQL statement:\ndelete from product where id=? [23503-232]] [delete from product where id=?]; SQL [delete from product where id=?]; constraint [FK_CART_ITEM_PRODUCT: PUBLIC.CART_ITEM FOREIGN KEY(PRODUCT_ID) REFERENCES PUBLIC.PRODUCT(ID) (CAST(10 AS BIGINT)); SQL statement:\ndelete from product where id=? [23503-232]]",
    @SecurityRequirement(name = "bearerAuth")
    @DeleteMapping("/{id}")
    public ResponseEntity<EntityModel<Product>> deleteProduct(@PathVariable Long id) {

        productService.deleteProduct(id);
        return ResponseEntity.noContent().build();
    }
}
