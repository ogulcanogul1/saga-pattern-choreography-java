package com.learning.payment.service.application.entity.model;

import com.learning.common.event.OrderStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

@Data @AllArgsConstructor @NoArgsConstructor @Builder
public class OrderDto {

    private UUID orderId;
    private List<OrderItemDto> orderItems;
    private OrderStatus orderStatus;
    private UUID customerId;
    private double totalPrice;

    public double getTotalPrice() {
        return orderItems.stream()
                .mapToDouble(OrderItemDto::getTotal)
                .sum();
    }
}
