package com.learning.order.service.application.event.publisher;

import com.learning.common.configuration.KafkaTopics;
import com.learning.common.payload.order.event.OrderCancelledEventPayload;
import com.learning.common.publisher.event.order.OrderCancelledEventPublisher;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class OrderCancelledEventPublisherImpl implements OrderCancelledEventPublisher {

   private final KafkaTemplate<String, Object> orderCancelledEventKafkaTemplate;

   private final static String TOPIC = KafkaTopics.ORDER_CANCELLED_EVENT;

    public OrderCancelledEventPublisherImpl(KafkaTemplate<String, Object> orderCancelledEventKafkaTemplate) {
        this.orderCancelledEventKafkaTemplate = orderCancelledEventKafkaTemplate;
    }

    @Override
    public void publish(OrderCancelledEventPayload event) {
        orderCancelledEventKafkaTemplate.send(TOPIC, event);
    }

    @Override
    public void publish(OrderCancelledEventPayload event, String key) {
        orderCancelledEventKafkaTemplate.send(TOPIC,event.orderId().toString() ,event);

    }
}
