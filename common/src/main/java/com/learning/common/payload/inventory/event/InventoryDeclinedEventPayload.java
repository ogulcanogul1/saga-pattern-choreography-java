package com.learning.common.payload.inventory.event;

import com.learning.common.event.InventoryStatus;

import java.util.UUID;


public record InventoryDeclinedEventPayload(UUID orderId) implements InventoryEventPayload {
}
