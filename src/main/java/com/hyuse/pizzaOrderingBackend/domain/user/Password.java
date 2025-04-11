package com.hyuse.pizzaOrderingBackend.domain.user;

import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Embeddable
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Password {

    @NotBlank(message = "Password is required")
    @Size(min = 8, message = "Password must be at least 6 characters long")
    private String passwordValue; // Mudando de "value" para "passwordValue"

}
