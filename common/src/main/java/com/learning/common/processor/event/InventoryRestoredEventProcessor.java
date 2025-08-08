package com.learning.common.processor.event;

import com.learning.common.payload.inventory.event.InventoryRestoredEventPayload;
import com.learning.common.payload.order.event.OrderCancelledEventPayload;
import com.learning.common.processor.EventProcessor;

public interface InventoryRestoredEventProcessor extends EventProcessor<InventoryRestoredEventPayload, OrderCancelledEventPayload> {
}
