package com.learning.common.payload.order.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data @NoArgsConstructor @AllArgsConstructor @Builder
public class OrderItemPayload {

    private UUID productId;
    private Integer quantity;
    private Double price;
}


