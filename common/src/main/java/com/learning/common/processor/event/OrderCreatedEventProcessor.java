package com.learning.common.processor.event;

import com.learning.common.payload.inventory.event.InventoryEventPayload;
import com.learning.common.payload.order.event.OrderCreatedEventPayload;
import com.learning.common.processor.EventProcessor;

public interface OrderCreatedEventProcessor extends EventProcessor<OrderCreatedEventPayload, InventoryEventPayload> {

}
