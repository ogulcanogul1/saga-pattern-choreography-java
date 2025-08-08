package com.learning.common.publisher.event.inventory;

import com.learning.common.payload.inventory.event.InventoryRestoredEventPayload;
import com.learning.common.publisher.EventPublisher;

public interface InventoryRestoredEventPublisher extends EventPublisher<InventoryRestoredEventPayload> {
}
