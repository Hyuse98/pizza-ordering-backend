package com.hyuse.pizzaOrderingBackend.user.service;

import com.hyuse.pizzaOrderingBackend.user.model.Name;
import com.hyuse.pizzaOrderingBackend.user.model.User;
import com.hyuse.pizzaOrderingBackend.user.dto.UserDTO;

import java.util.Collection;
import java.util.UUID;

public interface UserServiceInterface {

    User createUser(String email, String rawPassword);
    User updateUser(UUID id, UserDTO userDTO);

    User findUserById(UUID id);
    User findUserByName(Name name);
    User findUserByEmail(String emailAddress);
    Collection<User> findAllUsers();

    void deleteUser(UUID id);
}
