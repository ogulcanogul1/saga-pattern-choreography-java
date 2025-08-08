package com.learning.inventory.service.application.event.publisher;

import com.learning.common.configuration.KafkaTopics;
import com.learning.common.payload.inventory.event.InventoryRestoredEventPayload;
import com.learning.common.publisher.event.inventory.InventoryRestoredEventPublisher;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class InventoryRestoredEventPublisherImpl implements InventoryRestoredEventPublisher {

    private final KafkaTemplate<String, Object> kafkaTemplate;

    private final static String TOPIC = KafkaTopics.INVENTORY_RESTORED_EVENT;

    public InventoryRestoredEventPublisherImpl(KafkaTemplate<String, Object> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    @Override
    public void publish(InventoryRestoredEventPayload event) {
        kafkaTemplate.send(TOPIC,event);
    }

    @Override
    public void publish(InventoryRestoredEventPayload event, String key) {
        kafkaTemplate.send(TOPIC,key,event);
    }
}
