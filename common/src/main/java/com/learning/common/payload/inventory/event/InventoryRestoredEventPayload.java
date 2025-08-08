package com.learning.common.payload.inventory.event;



import java.util.UUID;

public record InventoryRestoredEventPayload(UUID orderId) implements InventoryEventPayload {
}
