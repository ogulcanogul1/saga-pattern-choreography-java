package com.learning.inventory.service.application.entity.model;

import com.learning.common.event.OrderStatus;
import lombok.*;

import java.util.List;
import java.util.UUID;

@Data @AllArgsConstructor @NoArgsConstructor @Builder
public class OrderDto {

    private UUID orderId;
    private List<OrderItemDto> orderItems;
    private OrderStatus orderStatus;
    private UUID customerId;
    private double totalPrice;
}
