package com.learning.common.processor.event;

import com.learning.common.payload.inventory.event.InventoryReservedEventPayload;
import com.learning.common.payload.payment.event.PaymentEventPayload;
import com.learning.common.processor.EventProcessor;

public interface InventoryReservedEventProcessor extends EventProcessor<InventoryReservedEventPayload, PaymentEventPayload> {

}
