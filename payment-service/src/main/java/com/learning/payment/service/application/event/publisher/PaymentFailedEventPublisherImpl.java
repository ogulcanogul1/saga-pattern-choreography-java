package com.learning.payment.service.application.event.publisher;

import com.learning.common.configuration.KafkaTopics;
import com.learning.common.payload.payment.event.PaymentFailedEventPayload;
import com.learning.common.publisher.event.payment.PaymentFailedEventPublisher;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class PaymentFailedEventPublisherImpl implements PaymentFailedEventPublisher {

    private final KafkaTemplate<String, Object> paymentFailedEventEventPublisher;

    private final static String TOPIC = KafkaTopics.PAYMENT_FAILED_EVENT;

    public PaymentFailedEventPublisherImpl(KafkaTemplate<String, Object> paymentFailedEventEventPublisher) {
        this.paymentFailedEventEventPublisher = paymentFailedEventEventPublisher;
    }

    @Override
    public void publish(PaymentFailedEventPayload event) {
        paymentFailedEventEventPublisher.send(TOPIC,event);
    }

    @Override
    public void publish(PaymentFailedEventPayload event, String key) {
        paymentFailedEventEventPublisher.send(TOPIC, key, event);
    }
}
