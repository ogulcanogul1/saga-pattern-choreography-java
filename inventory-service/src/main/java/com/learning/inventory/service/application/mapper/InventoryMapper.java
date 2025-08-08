package com.learning.inventory.service.application.mapper;

import com.learning.common.payload.order.model.OrderItemPayload;
import com.learning.common.payload.order.model.OrderPayload;
import com.learning.inventory.service.application.entity.model.OrderDto;
import com.learning.inventory.service.application.entity.model.OrderItemDto;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class InventoryMapper {

    public List<OrderItemDto> orderItemPayloadToOrderItemDto(List<OrderItemPayload> orderItemPayloads) {

        return orderItemPayloads.stream().map(orderItemPayload ->
                OrderItemDto.builder()
                .productId(orderItemPayload.getProductId())
                .quantity(orderItemPayload.getQuantity())
                .price(orderItemPayload.getPrice())
                .build()).toList();
    }

    public OrderDto orderPayloadToOrderDto(OrderPayload orderPayload) {
        return OrderDto.builder()
                .orderId(orderPayload.getOrderId())
                .customerId(orderPayload.getCustomerId())
                .totalPrice(orderPayload.getTotalPrice())
                .orderItems(orderItemPayloadToOrderItemDto(orderPayload.getOrderItems()))
                .orderStatus(orderPayload.getOrderStatus())
                .build();
    }

    public OrderPayload orderDtoToOrderPayload(OrderDto orderDto) {
        return OrderPayload.builder()
                .orderId(orderDto.getOrderId())
                .customerId(orderDto.getCustomerId())
                .totalPrice(orderDto.getTotalPrice())
                .orderItems(orderDto.getOrderItems().stream()
                        .map(item -> OrderItemPayload.builder()
                                .productId(item.getProductId())
                                .quantity(item.getQuantity())
                                .price(item.getPrice())
                                .build()).toList())
                .orderStatus(orderDto.getOrderStatus())
                .build();
    }
}
