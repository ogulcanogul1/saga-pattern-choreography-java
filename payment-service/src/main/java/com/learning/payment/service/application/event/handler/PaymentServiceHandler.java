package com.learning.payment.service.application.event.handler;

import com.learning.common.payload.inventory.event.InventoryReservedEventPayload;

public interface PaymentServiceHandler {

    void handlePaymentEvent(InventoryReservedEventPayload event);

}
