package com.hyuse.pizzaOrderingBackend.order.internal.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateOrderRequest {
    private String deliveryAddress;
    private String contactPhone;
    private String paymentMethod;
    private boolean useUserAddress = false;
} 