package com.learning.order.service.application.event.processor;

import com.learning.common.payload.inventory.event.InventoryRestoredEventPayload;
import com.learning.common.payload.order.event.OrderCancelledEventPayload;
import com.learning.common.processor.event.InventoryRestoredEventProcessor;
import com.learning.order.service.common.service.OrderService;
import org.springframework.stereotype.Component;

@Component
public class InventoryRestoredEventProcessorImpl implements InventoryRestoredEventProcessor {

    private final OrderService orderService;

    public InventoryRestoredEventProcessorImpl(OrderService orderService) {
        this.orderService = orderService;
    }

    @Override
    public OrderCancelledEventPayload process(InventoryRestoredEventPayload event) {


        OrderCancelledEventPayload payload =  orderService.rollback(event.orderId());


        return payload;
    }
}
