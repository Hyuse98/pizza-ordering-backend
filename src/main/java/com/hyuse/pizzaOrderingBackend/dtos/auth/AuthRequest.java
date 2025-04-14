package com.hyuse.pizzaOrderingBackend.dtos.auth;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class AuthRequest {

    private String email;
    private String password;
}
