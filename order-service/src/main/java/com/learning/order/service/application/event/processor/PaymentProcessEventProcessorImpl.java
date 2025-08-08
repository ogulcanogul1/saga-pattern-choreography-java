package com.learning.order.service.application.event.processor;

import com.learning.common.payload.order.event.OrderCompletedEventPayload;
import com.learning.common.payload.payment.event.PaymentProcessedEventPayload;
import com.learning.common.processor.event.PaymentProcessEventProcessor;
import com.learning.order.service.common.service.OrderService;
import org.springframework.stereotype.Component;

@Component
public class PaymentProcessEventProcessorImpl implements PaymentProcessEventProcessor {

    private final OrderService orderService;

    public PaymentProcessEventProcessorImpl(OrderService orderService) {
        this.orderService = orderService;
    }

    @Override
    public OrderCompletedEventPayload process(PaymentProcessedEventPayload event) {
        return orderService.OrderCompletedEventProcess(event) ;
    }
}
