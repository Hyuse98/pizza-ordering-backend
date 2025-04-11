package com.hyuse.pizzaOrderingBackend.domain.user;

import jakarta.persistence.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Entity
@AllArgsConstructor
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @NotNull(message = "Name is Required")
    @Valid
    @Embedded
    @Getter
    private Name name;

    @NotNull(message = "Email is Required")
    @Valid
    @Embedded
    @Getter
    private Email email;

    @NotNull(message = "Phone is Required")
    @Valid
    @Embedded
    @Getter
    private Phone phone;

    @NotNull(message = "Password is Required")
    @Valid
    @Embedded
    @Getter
    private Password password;

    @NotNull(message = "Address is Required")
    @Valid
    @Embedded
    @Getter
    private Address address;

    private boolean active;

    public User(Name name, Email email, Password password, Address address, Phone phone) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.address = address;
        this.phone = phone;
        this.active = true;
    }
}
