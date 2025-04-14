package com.hyuse.pizzaOrderingBackend.user.api;

import com.hyuse.pizzaOrderingBackend.user.internal.util.UserAssembler;
import com.hyuse.pizzaOrderingBackend.user.internal.model.Name;
import com.hyuse.pizzaOrderingBackend.user.internal.model.User;
import com.hyuse.pizzaOrderingBackend.user.api.dto.UserDTO;
import com.hyuse.pizzaOrderingBackend.user.internal.service.UserServiceImpl;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
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

//    @PostMapping
//    public ResponseEntity<EntityModel<User>> createUser(@RequestBody @Valid UserDTO userDTO) {
//
//        var createdUser = userService.createUser(userDTO);
//        EntityModel<User> entityModel = userAssembler.toModel(createdUser);
//        return ResponseEntity.status(HttpStatus.CREATED).body(entityModel);
//    }

    @SecurityRequirement(name = "bearerAuth")
    @GetMapping("/{id}")
    public ResponseEntity<EntityModel<User>> getUserById(@PathVariable UUID id) {

        var user = userService.findUserById(id);
        EntityModel<User> entityModel = userAssembler.toModel(user);
        return ResponseEntity.ok(entityModel);
    }

    @SecurityRequirement(name = "bearerAuth")
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

    @SecurityRequirement(name = "bearerAuth")
    @GetMapping("/email/{emailAddress}")
    public ResponseEntity<EntityModel<User>> getUserByEmail(@PathVariable String emailAddress) {

        var user = userService.findUserByEmail(emailAddress);
        EntityModel<User> entityModel = userAssembler.toModel(user);
        return ResponseEntity.ok(entityModel);
    }

    @SecurityRequirement(name = "bearerAuth")
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

    @SecurityRequirement(name = "bearerAuth")
    @PutMapping("/{id}")
    public ResponseEntity<EntityModel<User>> updateUser(@PathVariable UUID id, @RequestBody @Valid UserDTO userDTO) {

        var updatedUser = userService.updateUser(id, userDTO);
        EntityModel<User> entityModel = userAssembler.toModel(updatedUser);
        return ResponseEntity.ok(entityModel);
    }

    @SecurityRequirement(name = "bearerAuth")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable UUID id) {

        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }
}
