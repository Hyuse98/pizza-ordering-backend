package com.hyuse.pizzaOrderingBackend.auth.service;

import com.hyuse.pizzaOrderingBackend.auth.dto.AuthRequestDTO;
import com.hyuse.pizzaOrderingBackend.auth.dto.AuthResponseDTO;
import org.springframework.http.ResponseEntity;

public interface AuthServiceInterface {

    void register(AuthRequestDTO request);
    ResponseEntity<AuthResponseDTO> login(AuthRequestDTO request);
}
