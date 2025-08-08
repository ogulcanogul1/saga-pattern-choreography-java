package com.learning.common.publisher.event.order;

import com.learning.common.payload.order.event.OrderCancelledEventPayload;
import com.learning.common.publisher.EventPublisher;

public interface OrderCancelledEventPublisher extends EventPublisher<OrderCancelledEventPayload> {
}
