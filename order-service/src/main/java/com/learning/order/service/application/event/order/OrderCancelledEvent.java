package com.learning.order.service.application.event.order;

import java.util.UUID;

public record OrderCancelledEvent(
    UUID orederId
) implements OrderEvent {
}
