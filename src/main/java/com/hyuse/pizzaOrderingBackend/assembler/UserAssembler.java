package com.hyuse.pizzaOrderingBackend.assembler;

import com.hyuse.pizzaOrderingBackend.controller.UserController;
import com.hyuse.pizzaOrderingBackend.domain.user.User;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class UserAssembler implements RepresentationModelAssembler<User, EntityModel<User>> {

    @Override
    public EntityModel<User> toModel(User user) {
        return EntityModel.of(user,
                linkTo(methodOn(UserController.class).getUserByFullName(user.getName().toString())).withSelfRel(),
                linkTo(methodOn(UserController.class).getAllUsers()).withRel("allUsers"));
    }
}
