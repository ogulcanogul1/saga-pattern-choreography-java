package com.learning.inventory.service.application.event.inventory;

import com.learning.common.payload.order.model.OrderPayload;

public record InventoryRestoredEvent(
        OrderPayload orderPayload
) implements InventoryEvent {
}
