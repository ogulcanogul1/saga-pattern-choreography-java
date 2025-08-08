package com.learning.inventory.service.application.event.inventory;

import com.learning.common.payload.inventory.event.InventoryRestoredEventPayload;

public record InventoryReservedEvent(
        InventoryRestoredEventPayload inventoryRestoredEventPayload

) implements InventoryEvent {
}
