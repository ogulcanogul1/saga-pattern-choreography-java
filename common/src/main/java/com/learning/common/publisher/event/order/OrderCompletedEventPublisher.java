package com.learning.common.publisher.event.order;

import com.learning.common.payload.order.event.OrderCompletedEventPayload;
import com.learning.common.publisher.EventPublisher;

public interface OrderCompletedEventPublisher extends EventPublisher<OrderCompletedEventPayload> {
}
