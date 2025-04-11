package com.hyuse.pizzaOrderingBackend.domain.user;

import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Embeddable
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Email {

    @jakarta.validation.constraints.Email
    @NotBlank(message = "Email address is required")
    private String emailAddress;

}
