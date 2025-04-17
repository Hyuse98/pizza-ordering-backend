package com.hyuse.pizzaOrderingBackend.auth.service;

import com.hyuse.pizzaOrderingBackend.auth.dto.UserCreationMessage;

public interface UserCreationProducerInterface {

    void send(UserCreationMessage message);
}
