package com.learning.inventory.service.application.event.handler;

import com.learning.common.configuration.KafkaTopics;
import com.learning.common.payload.order.event.OrderCreatedEventPayload;
import com.learning.common.payload.payment.event.PaymentFailedEventPayload;
import com.learning.common.processor.event.OrderCreatedEventProcessor;
import com.learning.common.processor.event.PaymentFailedEventProcessor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaHandler;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;


@Slf4j
@Component
@KafkaListener(topics = {KafkaTopics.ORDER_CREATED_EVENT,KafkaTopics.PAYMENT_FAILED_EVENT},groupId = "${spring.kafka.consumer.group-id}",
containerFactory = "kafkaListenerContainerFactory")
public class InventoryServiceHandlerImpl implements InventoryServiceHandler {

    private final OrderCreatedEventProcessor orderCreatedEventProcessor;
    private final PaymentFailedEventProcessor paymentFailedEventProcessor;

    public InventoryServiceHandlerImpl(OrderCreatedEventProcessor orderCreatedEventProcessor, PaymentFailedEventProcessor paymentFailedEventProcessor) {
        this.orderCreatedEventProcessor = orderCreatedEventProcessor;
        this.paymentFailedEventProcessor = paymentFailedEventProcessor;
    }


    @Override
    @KafkaHandler
    public void handle(OrderCreatedEventPayload orderCreatedEvent) {
        log.info("Received Order Created Event");
        orderCreatedEventProcessor.process(orderCreatedEvent);
    }

    @Override
    @KafkaHandler
    public void handle(PaymentFailedEventPayload paymentFailedEvent) {
        paymentFailedEventProcessor.process(paymentFailedEvent);
    }
}


