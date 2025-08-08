package com.learning.common.processor.event;


import com.learning.common.payload.inventory.event.InventoryDeclinedEventPayload;
import com.learning.common.payload.order.event.OrderCancelledEventPayload;
import com.learning.common.processor.EventProcessor;

public interface InventoryDeclinedEventProcessor extends EventProcessor<InventoryDeclinedEventPayload, OrderCancelledEventPayload> {
}
