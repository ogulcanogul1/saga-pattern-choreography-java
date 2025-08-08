package com.learning.order.service.application.event.publisher;

import com.learning.common.configuration.KafkaTopics;
import com.learning.common.payload.order.event.OrderCreatedEventPayload;
import com.learning.common.publisher.event.order.OrderCreatedEventPublisher;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class OrderCreatedEventPublisherImpl implements OrderCreatedEventPublisher {

    private final KafkaTemplate<String, Object> kafkaTemplate;
    private final static String TOPIC = KafkaTopics.ORDER_CREATED_EVENT;

    public OrderCreatedEventPublisherImpl(KafkaTemplate<String, Object> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    @Override
    public void publish(OrderCreatedEventPayload event) {
        kafkaTemplate.send(TOPIC, event);
    }

    @Override
    public void publish(OrderCreatedEventPayload event, String key) {
        kafkaTemplate.send(TOPIC, key,event);
    }
}
