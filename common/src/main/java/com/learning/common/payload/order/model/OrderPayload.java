package com.learning.common.payload.order.model;

import com.learning.common.event.OrderStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

@Data @AllArgsConstructor @Builder @NoArgsConstructor
public class OrderPayload {
    private UUID orderId;
    private List<OrderItemPayload> orderItems;
    private OrderStatus orderStatus;
    private UUID customerId;
    private double totalPrice;
}


