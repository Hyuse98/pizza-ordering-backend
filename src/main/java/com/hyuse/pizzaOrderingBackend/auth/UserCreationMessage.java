package com.hyuse.pizzaOrderingBackend.auth;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class UserCreationMessage {

    public UserCreationMessage(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public UserCreationMessage() {
    }

    private String email;
    private String password;
}
