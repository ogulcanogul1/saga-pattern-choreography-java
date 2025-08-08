package com.learning.common.payload.order.event;

import com.learning.common.payload.order.model.OrderPayload;

public record OrderCompletedEventPayload(OrderPayload order) implements OrderEventPayload {
}
