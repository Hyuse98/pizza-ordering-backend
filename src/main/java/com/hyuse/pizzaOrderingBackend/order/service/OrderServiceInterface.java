package com.hyuse.pizzaOrderingBackend.order.service;

import com.hyuse.pizzaOrderingBackend.order.model.Order;

public interface OrderServiceInterface {

    Order finishOrder(int clienteId, String formaPagamento, String formaEntrega, String observacoes);

    Order getOrder(int pedidoId);

    String orderStatus(int pedidoId);
}
