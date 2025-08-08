package com.learning.order.service.application.event.publisher;

import com.learning.common.configuration.KafkaTopics;
import com.learning.common.payload.order.event.OrderCompletedEventPayload;
import com.learning.common.publisher.event.order.OrderCompletedEventPublisher;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class OrderCompletedEventPublisherImpl implements OrderCompletedEventPublisher {

    private final KafkaTemplate<String,Object> kafkaTemplate;
    private final static String TOPIC = KafkaTopics.ORDER_COMPLETED_EVENT;

    public OrderCompletedEventPublisherImpl(KafkaTemplate<String, Object> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }


    @Override
    public void publish(OrderCompletedEventPayload event) {
        kafkaTemplate.send(TOPIC , event);
    }

    @Override
    public void publish(OrderCompletedEventPayload event, String key) {
        kafkaTemplate.send(TOPIC , key ,event);
    }
}
