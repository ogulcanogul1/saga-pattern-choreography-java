package com.learning.order.service.application.mapper;

import com.learning.common.payload.order.model.OrderItemPayload;
import com.learning.common.payload.order.model.OrderPayload;
import com.learning.order.service.application.entity.Order;
import com.learning.order.service.application.entity.OrderItem;
import com.learning.order.service.common.dto.CreateOrderRequest;
import com.learning.order.service.common.dto.CreateOrderResponse;
import com.learning.order.service.common.dto.OrderItemDto;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Component
public class OrderMapper {

    public List<OrderItem> orderItemDtosToOrderItems(List<OrderItemDto> orderItemDtos, Order order) {
        return orderItemDtos.stream()
                .map(dto -> OrderItem.builder()
                        .price(dto.getPrice())
                        .productId(dto.getProductId())
                        .quantity(dto.getQuantity())
                        .order(order)
                        .build())
                .collect(Collectors.toList());
    }

    public List<OrderItem> orderItemDtosToOrderItems(List<OrderItemDto> orderItemDtos) {
        return orderItemDtos.stream()
                .map(dto -> OrderItem.builder()
                        .price(dto.getPrice())
                        .productId(dto.getProductId())
                        .quantity(dto.getQuantity())
                        .build())
                .collect(Collectors.toList());
    }


    public Order createOrderRequestToOrder(CreateOrderRequest createOrderRequest, UUID orderId){
       Order order =  Order.builder()
               .id(orderId)
               .totalPrice(createOrderRequest.getTotalPrice())
               .customerId(createOrderRequest.getCustomerId())
               .orderItems(orderItemDtosToOrderItems(createOrderRequest.getOrderItems()))
               .build();

        List<OrderItem> orderItems = orderItemDtosToOrderItems(createOrderRequest.getOrderItems(), order);
        order.setOrderItems(orderItems);

        return order;
    }



    public CreateOrderResponse orderToCreateOrderResponse(Order order){
        return CreateOrderResponse.builder()
                .orderId(order.getId())
                .build();
    }

    public OrderPayload orderToOrderPayload(Order order){
        return OrderPayload.builder()
                .orderId(order.getId())
                .orderItems(orderItemsToOrderItemPayload(order.getOrderItems()))
                .orderStatus(order.getOrderStatus())
                .customerId(order.getCustomerId())
                .totalPrice(order.getTotalPrice())
                .build();
    }

    public List<OrderItemPayload> orderItemsToOrderItemPayload(List<OrderItem> orderItems){

        return orderItems.stream()
                .map(orderItem -> OrderItemPayload.builder()
                        .productId(orderItem.getProductId())
                        .quantity(orderItem.getQuantity())
                        .price(orderItem.getPrice())
                        .build())
                .collect(Collectors.toList());
    }
}
