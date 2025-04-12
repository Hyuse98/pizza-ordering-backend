package com.hyuse.pizzaOrderingBackend.service;

import com.hyuse.pizzaOrderingBackend.domain.user.Name;
import com.hyuse.pizzaOrderingBackend.domain.user.User;

import java.util.Collection;
import java.util.UUID;

public interface UserServiceInterface {

    User createUser(User user);
    User findUserById(UUID id);
    User findUserByName(Name name);
    User findUserByEmail(String emailAddress);
    Collection<User> findAllUsers();
    User updateUser(UUID id, User user);
    void deleteUser(UUID id);

}
