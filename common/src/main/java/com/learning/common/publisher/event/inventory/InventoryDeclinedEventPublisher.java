package com.learning.common.publisher.event.inventory;

import com.learning.common.payload.inventory.event.InventoryDeclinedEventPayload;
import com.learning.common.publisher.EventPublisher;

public interface InventoryDeclinedEventPublisher extends EventPublisher<InventoryDeclinedEventPayload> {
}
