package com.learning.order.service.application.event.handler;

import com.learning.common.payload.inventory.event.InventoryDeclinedEventPayload;
import com.learning.common.payload.inventory.event.InventoryRestoredEventPayload;
import com.learning.common.payload.payment.event.PaymentProcessedEventPayload;

public interface OrderServiceHandler {

    void handleOrderCreatedEvent(InventoryDeclinedEventPayload inventoryDeclinedEventPayload);
    void handleInventoryRestoredEvent(InventoryRestoredEventPayload inventoryRestoredEventPayload);
    void handlePaymentProcessedEvent(PaymentProcessedEventPayload paymentProcessedEventPayload);
}
