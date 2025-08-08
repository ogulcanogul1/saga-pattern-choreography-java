package com.learning.payment.service.application.event.publisher;

import com.learning.common.configuration.KafkaTopics;
import com.learning.common.payload.payment.event.PaymentProcessedEventPayload;
import com.learning.common.publisher.event.payment.PaymentProcessEventPublisher;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;


@Component
public class PaymentProcessedEventPublisherImpl implements PaymentProcessEventPublisher {

    private final KafkaTemplate<String,Object> paymentFailedEventEventPublisher;
    private final static String TOPIC = KafkaTopics.PAYMENT_PROCESS_EVENT;

    public PaymentProcessedEventPublisherImpl(KafkaTemplate<String, Object> paymentFailedEventEventPublisher) {
        this.paymentFailedEventEventPublisher = paymentFailedEventEventPublisher;
    }

    @Override
    public void publish(PaymentProcessedEventPayload event) {

        paymentFailedEventEventPublisher.send(TOPIC, event);
    }

    @Override
    public void publish(PaymentProcessedEventPayload event, String key) {

        paymentFailedEventEventPublisher.send(TOPIC, key, event);
    }
}
