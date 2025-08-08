package com.learning.inventory.service.application.event.inventory;

import java.util.UUID;

public record InventoryDeclinedEvent(
        UUID orderId
) implements InventoryEvent {
}
