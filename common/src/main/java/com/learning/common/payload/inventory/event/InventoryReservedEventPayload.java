package com.learning.common.payload.inventory.event;

import com.learning.common.payload.inventory.model.InventoryPayload;

import java.util.UUID;


public record InventoryReservedEventPayload(InventoryPayload inventory, UUID customerId) implements InventoryEventPayload {

}
