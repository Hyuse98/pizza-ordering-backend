package com.hyuse.pizzaOrderingBackend.infra.internal.exceptions;

import java.time.LocalDateTime;

public class ErrorResponse {

    private int status;
    private String message;
    private LocalDateTime timestamp;

    public ErrorResponse() {}

    public ErrorResponse(int status, String message, LocalDateTime timestamp) {
        this.status = status;
        this.message = message;
        this.timestamp = timestamp;
    }
}
