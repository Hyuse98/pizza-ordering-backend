package com.hyuse.pizzaOrderingBackend.user.dto;

import jakarta.validation.constraints.Pattern;

public record UserDTO(

        String firstName,

        String lastName,

        String emailAddress,

        @Pattern(regexp = "\\d{10,11}", message = "Invalid phone number format (e.g., 10 or 11 digits)")
        String phoneNumber,

        String passwordValue,

        String country,

        String state,

        String city,

        String neighborhood,

        String street,

        String houseNumber,

        String zipCode
) {
}