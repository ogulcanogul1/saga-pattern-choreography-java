package com.learning.common.payload.order.event;

import com.learning.common.event.OrderStatus;
import com.learning.common.payload.order.model.OrderPayload;

public record OrderCreatedEventPayload(OrderPayload orderPayload) implements OrderEventPayload {
}
