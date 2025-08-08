package com.learning.order.service.common.service;

import com.learning.common.payload.order.event.OrderCancelledEventPayload;
import com.learning.common.payload.order.event.OrderCompletedEventPayload;
import com.learning.common.payload.payment.event.PaymentProcessedEventPayload;
import com.learning.order.service.common.dto.CreateOrderRequest;
import com.learning.order.service.common.dto.CreateOrderResponse;

import java.util.UUID;

public interface OrderService {

    CreateOrderResponse createOrder(CreateOrderRequest createOrderRequest);

    OrderCancelledEventPayload rollback(UUID orderId);

    OrderCompletedEventPayload OrderCompletedEventProcess(PaymentProcessedEventPayload payload);
}
