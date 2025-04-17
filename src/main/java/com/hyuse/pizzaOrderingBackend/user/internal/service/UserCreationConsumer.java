package com.hyuse.pizzaOrderingBackend.user.internal.service;

import com.hyuse.pizzaOrderingBackend.auth.dto.UserCreationMessage;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
public class UserCreationConsumer {

    private final UserServiceInterface userService;

    public UserCreationConsumer(UserServiceInterface userService) {
        this.userService = userService;
    }

    @RabbitListener(queues = "auth.user.create.queue")
    public void consume(UserCreationMessage message) {
        System.out.println("Mensagem recebida: " + message);
        userService.createUser(message.getEmail(), message.getPassword());
    }
}

