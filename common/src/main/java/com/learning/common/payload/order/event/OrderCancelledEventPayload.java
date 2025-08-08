package com.learning.common.payload.order.event;

import java.util.UUID;

public record OrderCancelledEventPayload(UUID orderId) implements OrderEventPayload
{
}
