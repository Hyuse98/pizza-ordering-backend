package com.hyuse.pizzaOrderingBackend.auth.internal.service;

import com.hyuse.pizzaOrderingBackend.auth.UserCreationMessage;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@Service
public class UserCreationProducer {
    private final RabbitTemplate rabbitTemplate;

    public UserCreationProducer(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void send(UserCreationMessage message) {
        rabbitTemplate.convertAndSend("auth.user.exchange", "user.create", message);
    }
}

