package com.learning.inventory.service.application.event.processor;

import com.learning.common.payload.inventory.event.InventoryRestoredEventPayload;
import com.learning.common.payload.payment.event.PaymentFailedEventPayload;
import com.learning.common.processor.event.PaymentFailedEventProcessor;
import com.learning.inventory.service.common.service.InventoryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class PaymentFailedEventProcessorImpl implements PaymentFailedEventProcessor {

    private final InventoryService inventoryService;

    public PaymentFailedEventProcessorImpl(InventoryService inventoryService) {
        this.inventoryService = inventoryService;
    }

    @Override
    public InventoryRestoredEventPayload process(PaymentFailedEventPayload event) {

        InventoryRestoredEventPayload inventoryRestoredEventPayload = inventoryService.processInventoryRestoration(event);

        log.info("Inventory restored event payload: {}", inventoryRestoredEventPayload);


        return inventoryRestoredEventPayload;
    }
}
