package com.learning.common.publisher.event.order;

import com.learning.common.payload.order.event.OrderCreatedEventPayload;
import com.learning.common.publisher.EventPublisher;

public interface OrderCreatedEventPublisher extends EventPublisher<OrderCreatedEventPayload> {
}
