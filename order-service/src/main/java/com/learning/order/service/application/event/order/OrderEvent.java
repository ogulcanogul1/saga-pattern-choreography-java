package com.learning.order.service.application.event.order;

import com.learning.common.event.DomainEvent;

public sealed interface OrderEvent extends DomainEvent permits OrderCreatedEvent , OrderCancelledEvent , OrderCompletedEvent{

}
