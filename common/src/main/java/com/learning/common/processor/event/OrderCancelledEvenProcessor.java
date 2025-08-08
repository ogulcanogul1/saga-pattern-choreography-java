package com.learning.common.processor.event;

import com.learning.common.payload.EmptyPayload;
import com.learning.common.payload.order.event.OrderCancelledEventPayload;
import com.learning.common.processor.EventProcessor;

public interface OrderCancelledEvenProcessor extends EventProcessor<OrderCancelledEventPayload, EmptyPayload> {
}
