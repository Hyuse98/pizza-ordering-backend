package com.hyuse.pizzaOrderingBackend.infra.internal.exceptions;

import java.time.LocalDateTime;
import java.util.Map;

public class ValidationErrorResponse extends RuntimeException {

    public ValidationErrorResponse(int value, String erroDeValidação, LocalDateTime now, Map<String, String> errors) {
    }
}
