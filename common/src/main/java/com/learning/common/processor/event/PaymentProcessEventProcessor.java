package com.learning.common.processor.event;

import com.learning.common.payload.order.event.OrderCompletedEventPayload;
import com.learning.common.payload.payment.event.PaymentProcessedEventPayload;
import com.learning.common.processor.EventProcessor;

public interface PaymentProcessEventProcessor extends EventProcessor<PaymentProcessedEventPayload, OrderCompletedEventPayload> {
}
