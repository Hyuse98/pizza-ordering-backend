package com.hyuse.pizzaOrderingBackend.user.util;

import com.hyuse.pizzaOrderingBackend.user.dto.UserDTO;
import com.hyuse.pizzaOrderingBackend.user.model.*;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    private final PasswordEncoder passwordEncoder;

    public UserMapper(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    public User toEntity(UserDTO userDTO) {

        Name name = new Name(
                userDTO.firstName(),
                userDTO.lastName());

        Email email = new Email(
                userDTO.emailAddress());

        Password password = new Password(
                passwordEncoder.encode(userDTO.passwordValue()));

        Phone phone = new Phone(
                userDTO.phoneNumber());

        Address address = new Address(
                userDTO.country(),
                userDTO.neighborhood(),
                userDTO.street(),
                userDTO.houseNumber(),
                userDTO.city(),
                userDTO.state(),
                userDTO.zipCode()
        );

        return new User(name, email, password, address, phone);
    }

    public UserDTO toDto(User user) {

        return new UserDTO(
                user.getName().getFirstName(),
                user.getName().getLastName(),
                user.getEmail().getEmailAddress(),
                user.getPhone().getPhoneNumber(),
                user.getPassword().getPasswordValue(),
                user.getAddress().getCountry(),
                user.getAddress().getState(),
                user.getAddress().getCity(),
                user.getAddress().getNeighborhood(),
                user.getAddress().getStreet(),
                user.getAddress().getHouseNumber(),
                user.getAddress().getZipCode()
        );
    }
}