package com.learning.order.service.common.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Data @Builder
@AllArgsConstructor
public class CreateOrderResponse {
    UUID orderId;
}
