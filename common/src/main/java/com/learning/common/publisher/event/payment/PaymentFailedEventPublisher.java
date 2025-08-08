package com.learning.common.publisher.event.payment;

import com.learning.common.payload.payment.event.PaymentFailedEventPayload;
import com.learning.common.publisher.EventPublisher;

public interface PaymentFailedEventPublisher extends EventPublisher<PaymentFailedEventPayload> {
}
