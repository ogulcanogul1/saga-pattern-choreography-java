package com.learning.payment.service.application.event.processor;

import com.learning.common.payload.inventory.event.InventoryReservedEventPayload;
import com.learning.common.payload.payment.event.PaymentEventPayload;
import com.learning.common.processor.event.InventoryReservedEventProcessor;
import com.learning.payment.service.common.service.PaymentService;
import org.springframework.stereotype.Component;

@Component
public class InventoryReservedEventProcessorImpl implements InventoryReservedEventProcessor {

    private final PaymentService paymentService;

    public InventoryReservedEventProcessorImpl(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @Override
    public PaymentEventPayload process(InventoryReservedEventPayload event) {

        PaymentEventPayload payload =  paymentService.paymentProcess(event.inventory(),event.customerId());
        return null;
    }
}
