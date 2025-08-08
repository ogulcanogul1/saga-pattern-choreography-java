package com.learning.payment.service.application.event.handler;

import com.learning.common.configuration.KafkaTopics;
import com.learning.common.payload.inventory.event.InventoryReservedEventPayload;
import com.learning.common.processor.event.InventoryReservedEventProcessor;
import org.springframework.kafka.annotation.KafkaHandler;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@KafkaListener(topics = KafkaTopics.INVENTORY_RESERVED_EVENT,groupId = "${spring.kafka.consumer.group-id}")
@Component
public class PaymentServiceHandlerImpl implements PaymentServiceHandler {

    private final InventoryReservedEventProcessor inventoryReservedEventProcessor;

    public PaymentServiceHandlerImpl(InventoryReservedEventProcessor inventoryReservedEventProcessor) {
        this.inventoryReservedEventProcessor = inventoryReservedEventProcessor;
    }

    @Override
    @KafkaHandler
    public void handlePaymentEvent(InventoryReservedEventPayload event) {
        inventoryReservedEventProcessor.process(event);
    }
}


