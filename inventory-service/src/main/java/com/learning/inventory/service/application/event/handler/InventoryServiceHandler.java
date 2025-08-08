package com.learning.inventory.service.application.event.handler;

import com.learning.common.payload.order.event.OrderCreatedEventPayload;
import com.learning.common.payload.payment.event.PaymentFailedEventPayload;

public interface  InventoryServiceHandler {

    void handle(OrderCreatedEventPayload orderCreatedEvent);

    void handle(PaymentFailedEventPayload paymentFailedEvent);

}
