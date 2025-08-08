package com.learning.order.service.common.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

@Data
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class CreateOrderRequest {
    private UUID customerId;
    private List<OrderItemDto> orderItems;

    private double totalPrice;
}
