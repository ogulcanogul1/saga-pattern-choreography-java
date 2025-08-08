package com.learning.inventory.service.application.event.publisher;

import com.learning.common.configuration.KafkaTopics;
import com.learning.common.payload.inventory.event.InventoryDeclinedEventPayload;
import com.learning.common.publisher.event.inventory.InventoryDeclinedEventPublisher;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class InventoryDeclinedEventPublisherImpl implements InventoryDeclinedEventPublisher {


    private final KafkaTemplate<String,Object> kafkaTemplate;

    private final static String topic = KafkaTopics.INVENTORY_DECLINED_EVENT;

    public InventoryDeclinedEventPublisherImpl(KafkaTemplate<String, Object> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    @Override
    public void publish(InventoryDeclinedEventPayload event) {
        kafkaTemplate.send(topic,event);
    }

    @Override
    public void publish(InventoryDeclinedEventPayload event, String key) {
        kafkaTemplate.send(topic,key,event);
    }
}
