package com.learning.inventory.service.common.service;

import com.learning.common.payload.inventory.event.InventoryEventPayload;
import com.learning.common.payload.inventory.event.InventoryRestoredEventPayload;
import com.learning.common.payload.order.model.OrderPayload;
import com.learning.common.payload.payment.event.PaymentFailedEventPayload;

import java.util.UUID;

public interface InventoryService {

    InventoryEventPayload checkInventory(OrderPayload orderPayload, UUID orderId);
    InventoryRestoredEventPayload processInventoryRestoration(PaymentFailedEventPayload eventPayload);
}
