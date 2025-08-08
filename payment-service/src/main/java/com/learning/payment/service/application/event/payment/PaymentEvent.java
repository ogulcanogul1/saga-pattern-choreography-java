package com.learning.payment.service.application.event.payment;

import com.learning.common.event.DomainEvent;

public sealed interface PaymentEvent extends DomainEvent
permits PaymentProcessedEvent , PaymentFailedEvent
{
}
