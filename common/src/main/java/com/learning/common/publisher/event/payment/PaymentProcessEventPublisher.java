package com.learning.common.publisher.event.payment;

import com.learning.common.payload.payment.event.PaymentProcessedEventPayload;
import com.learning.common.publisher.EventPublisher;

public interface PaymentProcessEventPublisher extends EventPublisher<PaymentProcessedEventPayload> {
}
