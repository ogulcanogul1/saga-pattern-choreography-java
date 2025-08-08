package com.learning.inventory.service.application.event.publisher;

import com.learning.common.configuration.KafkaTopics;
import com.learning.common.payload.inventory.event.InventoryReservedEventPayload;
import com.learning.common.publisher.event.inventory.InventoryReservedEventPublisher;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class InventoryReservedEventPublisherImpl implements InventoryReservedEventPublisher {

    private final KafkaTemplate<String, Object> kafkaTemplate;

    private final static String TOPIC = KafkaTopics.INVENTORY_RESERVED_EVENT;

    public InventoryReservedEventPublisherImpl(KafkaTemplate<String, Object> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    @Override
    public void publish(InventoryReservedEventPayload event) {
        kafkaTemplate.send(TOPIC,event);
    }

    @Override
    public void publish(InventoryReservedEventPayload event, String key) {
        kafkaTemplate.send(TOPIC,key,event);
    }

}
