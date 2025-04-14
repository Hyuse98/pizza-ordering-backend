package com.hyuse.pizzaOrderingBackend.order.internal.model;

public enum OrderStatus {
    PENDING,      // Pedido foi criado
    PREPARING,    // Cozinha está preparando
    READY,        // Pronto para entrega
    DELIVERING,   // Em rota de entrega
    DELIVERED,    // Entregue ao cliente
    CANCELLED     // Pedido cancelado
} 