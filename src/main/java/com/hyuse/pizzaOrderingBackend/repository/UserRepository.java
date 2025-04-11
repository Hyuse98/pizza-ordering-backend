package com.hyuse.pizzaOrderingBackend.repository;

import com.hyuse.pizzaOrderingBackend.domain.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {
}