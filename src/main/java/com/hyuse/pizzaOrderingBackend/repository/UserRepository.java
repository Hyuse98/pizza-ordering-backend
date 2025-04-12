package com.hyuse.pizzaOrderingBackend.repository;

import com.hyuse.pizzaOrderingBackend.domain.user.Name;
import com.hyuse.pizzaOrderingBackend.domain.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;
import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {

    @Query("SELECT u FROM User u WHERE u.name.firstName = :#{#name.firstName} AND u.name.lastName = :#{#name.lastName}")
    Optional<User> findByName(@Param("name") Name name);

    @Query("SELECT u FROM User u WHERE u.email.emailAddress = :emailAddress")
    Optional<User> findByEmailAddress(@Param("emailAddress") String emailAddress);

}