package com.hyuse.pizzaOrderingBackend.controller;

import com.hyuse.pizzaOrderingBackend.assembler.UserAssembler;
import com.hyuse.pizzaOrderingBackend.domain.user.Name;
import com.hyuse.pizzaOrderingBackend.domain.user.User;
import com.hyuse.pizzaOrderingBackend.service.UserServiceImpl;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.UUID;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("/api/user")
public class UserController {

    private final UserServiceImpl userService;
    private final UserAssembler userAssembler;

    @Autowired
    public UserController(UserServiceImpl userService, UserAssembler userAssembler) {
        this.userService = userService;
        this.userAssembler = userAssembler;
    }

    @PostMapping
    public ResponseEntity<EntityModel<User>> createUser(@RequestBody @Valid User user) {

        var createdUser = userService.createUser(user);
        EntityModel<User> entityModel = userAssembler.toModel(createdUser);
        return ResponseEntity.status(HttpStatus.CREATED).body(entityModel);
    }

    @GetMapping("/{id}")
    public ResponseEntity<EntityModel<User>> getUserById(@PathVariable UUID id) {

        User user = userService.findUserById(id);
        EntityModel<User> entityModel = userAssembler.toModel(user);
        return ResponseEntity.ok(entityModel);
    }

    @GetMapping("/name/{fullName}")
    public ResponseEntity<EntityModel<User>> getUserByFullName(@PathVariable String fullName) {

        String[] nameParts = fullName.split(" ", 2);

        if (nameParts.length < 2) {
            throw new IllegalArgumentException("Invalid Full Name Format");
        }
        String firstName = nameParts[0];
        String lastName = nameParts[1];
        
        Name name = new Name(firstName, lastName);
        User user = userService.findUserByName(name);
        EntityModel<User> entityModel = userAssembler.toModel(user);
        return ResponseEntity.ok(entityModel);
    }

    @GetMapping("/email/{emailAddress}")
    public ResponseEntity<EntityModel<User>> getUserByEmail(@PathVariable String emailAddress) {

        User user = userService.findUserByEmail(emailAddress);
        EntityModel<User> entityModel = userAssembler.toModel(user);
        return ResponseEntity.ok(entityModel);
    }

    @GetMapping
    public ResponseEntity<CollectionModel<EntityModel<User>>> getAllUsers() {

        Collection<User> users = userService.findAllUsers();
        Collection<EntityModel<User>> userEntityModels = users.stream()
                .map(userAssembler::toModel)
                .collect(Collectors.toList());

        CollectionModel<EntityModel<User>> collectionModel = CollectionModel.of(userEntityModels,
                linkTo(methodOn(UserController.class).getAllUsers()).withSelfRel());

        return ResponseEntity.ok(collectionModel);
    }

    @PutMapping("/{id}")
    public ResponseEntity<EntityModel<User>> updateUser(@PathVariable UUID id, @RequestBody @Valid User userDetails) {

        User updatedUser = userService.updateUser(id, userDetails);
        EntityModel<User> entityModel = userAssembler.toModel(updatedUser);
        return ResponseEntity.ok(entityModel);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable UUID id) {

        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }
}
