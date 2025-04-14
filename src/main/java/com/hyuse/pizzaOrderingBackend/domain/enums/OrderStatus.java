package com.hyuse.pizzaOrderingBackend.domain.enums;

public enum OrderStatus {
    PENDING,      // Pedido foi criado
    PREPARING,    // Cozinha está preparando
    READY,        // Pronto para entrega
    DELIVERING,   // Em rota de entrega
    DELIVERED,    // Entregue ao cliente
    CANCELLED     // Pedido cancelado
} 