package com.learning.order.service.common.dto;

import lombok.*;

import java.util.UUID;

@Data
@NoArgsConstructor
public class OrderItemDto {
    private UUID productId;
    private UUID orderId;
    private Integer quantity;
    private Double price;
}
