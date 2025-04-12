package com.hyuse.pizzaOrderingBackend.domain.user;

import jakarta.persistence.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Objects;
import java.util.UUID;

@Entity
@Table(
        name = "usersAccount"
)
@AllArgsConstructor
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Valid
    @NotNull(message = "Name is Required")
    @Embedded
    @Getter
    @Setter
    private Name name;

    @Valid
    @NotNull(message = "Email is Required")
    @Embedded
    @Getter
    @Setter
    private Email email;

    @Valid
    @NotNull(message = "Phone is Required")
    @Embedded
    @Getter
    @Setter
    private Phone phone;

    @Valid
    @NotNull(message = "Password is Required")
    @Embedded
    @Getter
    @Setter
    private Password password;

    @Valid
    @NotNull(message = "Address is Required")
    @Embedded
    @Getter
    @Setter
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(id, user.id) &&
                Objects.equals(name, user.name) &&
                Objects.equals(email, user.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, email);
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name=" + name +
                ", email=" + email +
                ", phone=" + phone +
                ", password=" + password +
                ", address=" + address +
                ", active=" + active +
                '}';
    }
}
