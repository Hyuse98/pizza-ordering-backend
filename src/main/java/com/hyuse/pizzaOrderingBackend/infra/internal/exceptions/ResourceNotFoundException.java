package com.hyuse.pizzaOrderingBackend.infra.internal.exceptions;

public class ResourceNotFoundException extends RuntimeException {

    public ResourceNotFoundException(String message) {
        super(message);
    }
}
