package com.learning.common.publisher.event.inventory;

import com.learning.common.payload.inventory.event.InventoryReservedEventPayload;
import com.learning.common.publisher.EventPublisher;

public interface InventoryReservedEventPublisher extends EventPublisher<InventoryReservedEventPayload> {
}
