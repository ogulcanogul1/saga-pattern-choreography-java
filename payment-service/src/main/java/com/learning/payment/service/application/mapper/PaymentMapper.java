package com.learning.payment.service.application.mapper;

import com.learning.common.payload.order.model.OrderItemPayload;
import com.learning.common.payload.order.model.OrderPayload;
import com.learning.payment.service.application.entity.model.OrderDto;
import com.learning.payment.service.application.entity.model.OrderItemDto;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PaymentMapper {

     public List<OrderItemDto> orderItemsPayloadToOrderItems(List<OrderItemPayload> orderItemPayloads) {
        return orderItemPayloads.stream()
                .map(orderItemPayload -> OrderItemDto.builder()
                        .productId(orderItemPayload.getProductId())
                        .quantity(orderItemPayload.getQuantity())
                        .price(orderItemPayload.getPrice())
                        .build())
                .toList();
    }

    public OrderDto orderPayloadToOrderDto(OrderPayload orderPayload) {
        return OrderDto.builder()
                .orderId(orderPayload.getOrderId())
                .customerId(orderPayload.getCustomerId())
                .totalPrice(orderPayload.getTotalPrice())
                .orderItems(orderItemsPayloadToOrderItems(orderPayload.getOrderItems()))
                .orderStatus(orderPayload.getOrderStatus())
                .build();
    }



}
