package com.hyuse.pizzaOrderingBackend.dtos.user;

import com.hyuse.pizzaOrderingBackend.domain.user.*;
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
                    userDTO.getFirstName(),
                    userDTO.getLastName());

            Email email = new Email(
                    userDTO.getEmailAddress());

            Password password = new Password(
                    passwordEncoder.encode(userDTO.getPasswordValue()));

            Phone phone = new Phone(
                    userDTO.getPhoneNumber());

            Address address = new Address(
                    userDTO.getCountry(),
                    userDTO.getNeighborhood(),
                    userDTO.getStreet(),
                    userDTO.getHouseNumber(),
                    userDTO.getCity(),
                    userDTO.getState(),
                    userDTO.getZipCode()
            );

            return new User(name, email, password, address, phone);
    }

    public UserDTO toDto(User user) {

        UserDTO userDTO = new UserDTO();

        userDTO.setFirstName(user.getName().getFirstName());
        userDTO.setLastName(user.getName().getLastName());

        userDTO.setEmailAddress(user.getEmail().getEmailAddress());

        userDTO.setPhoneNumber(user.getPhone().getPhoneNumber());

        userDTO.setCountry(user.getAddress().getCountry());
        userDTO.setState(user.getAddress().getState());
        userDTO.setCity(user.getAddress().getCity());
        userDTO.setNeighborhood(user.getAddress().getNeighborhood());
        userDTO.setStreet(user.getAddress().getStreet());
        userDTO.setHouseNumber(user.getAddress().getHouseNumber());
        userDTO.setZipCode(user.getAddress().getZipCode());

        return userDTO;
    }
}