package com.hyuse.pizzaOrderingBackend.domain.user;

import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Objects;

@Embeddable
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Password {

    @NotBlank(message = "Password is required")
    @Size(min = 8, message = "Password must be at least 6 characters long")
    private String passwordValue;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Password password = (Password) o;
        return Objects.equals(passwordValue, password.passwordValue);
    }

    @Override
    public int hashCode() {
        return Objects.hash(passwordValue);
    }

}
