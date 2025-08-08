package com.learning.order.service.application.event.handler;

import com.learning.common.configuration.KafkaTopics;
import com.learning.common.payload.inventory.event.InventoryDeclinedEventPayload;
import com.learning.common.payload.inventory.event.InventoryRestoredEventPayload;
import com.learning.common.payload.payment.event.PaymentProcessedEventPayload;
import com.learning.common.processor.event.InventoryDeclinedEventProcessor;
import com.learning.common.processor.event.InventoryRestoredEventProcessor;
import com.learning.common.processor.event.PaymentProcessEventProcessor;
import org.springframework.kafka.annotation.KafkaHandler;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@KafkaListener(topics = {KafkaTopics.INVENTORY_DECLINED_EVENT,
        KafkaTopics.INVENTORY_RESTORED_EVENT,KafkaTopics.PAYMENT_PROCESS_EVENT})
@Component
public class OrderServiceHandlerImpl implements OrderServiceHandler {
    private final InventoryDeclinedEventProcessor inventoryDeclinedEventProcessor;
    private final InventoryRestoredEventProcessor inventoryRestoredEventProcessor;
    private final PaymentProcessEventProcessor paymentProcessEventProcessor;

    public OrderServiceHandlerImpl(InventoryDeclinedEventProcessor inventoryDeclinedEventProcessor, InventoryRestoredEventProcessor inventoryRestoredEventProcessor, PaymentProcessEventProcessor paymentProcessEventProcessor) {
        this.inventoryDeclinedEventProcessor = inventoryDeclinedEventProcessor;
        this.inventoryRestoredEventProcessor = inventoryRestoredEventProcessor;
        this.paymentProcessEventProcessor = paymentProcessEventProcessor;
    }

    @KafkaHandler
    public void handleOrderCreatedEvent(InventoryDeclinedEventPayload inventoryDeclinedEventPayload) {
        inventoryDeclinedEventProcessor.process(inventoryDeclinedEventPayload);
    }

    @KafkaHandler
    public void handleInventoryRestoredEvent(InventoryRestoredEventPayload inventoryRestoredEventPayload) {
        inventoryRestoredEventProcessor.process(inventoryRestoredEventPayload);
    }

    @KafkaHandler
    public void handlePaymentProcessedEvent(PaymentProcessedEventPayload paymentProcessedEventPayload) {
        paymentProcessEventProcessor.process(paymentProcessedEventPayload);
    }


}
