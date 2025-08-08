package com.learning.order.service.application.event.processor;

import com.learning.common.payload.inventory.event.InventoryDeclinedEventPayload;
import com.learning.common.payload.order.event.OrderCancelledEventPayload;
import com.learning.common.processor.event.InventoryDeclinedEventProcessor;
import com.learning.order.service.common.service.OrderService;
import org.springframework.stereotype.Component;

@Component
public class InventoryDeclinedEventProcessorImpl implements InventoryDeclinedEventProcessor {

    private final OrderService orderService;

    public InventoryDeclinedEventProcessorImpl(OrderService orderService) {
        this.orderService = orderService;
    }

    @Override
    public OrderCancelledEventPayload process(InventoryDeclinedEventPayload event) {

        OrderCancelledEventPayload orderCancelledEventPayload = orderService.rollback(event.orderId());

        return orderCancelledEventPayload;
    }
}
