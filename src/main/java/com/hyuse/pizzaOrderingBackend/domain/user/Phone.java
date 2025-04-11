package com.hyuse.pizzaOrderingBackend.domain.user;

import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Embeddable
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Phone {
    @NotBlank(message = "Phone number is required")
    @Pattern(regexp = "\\d{10,11}", message = "Invalid phone number format (e.g., 10 or 11 digits)")
    private String phoneNumber; // Mudando de "number" para "phoneNumber"
}
