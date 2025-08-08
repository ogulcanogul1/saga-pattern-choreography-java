package com.learning.common.processor.event;

import com.learning.common.payload.inventory.event.InventoryRestoredEventPayload;
import com.learning.common.payload.payment.event.PaymentFailedEventPayload;
import com.learning.common.processor.EventProcessor;

public interface PaymentFailedEventProcessor extends EventProcessor<PaymentFailedEventPayload, InventoryRestoredEventPayload> {
}
