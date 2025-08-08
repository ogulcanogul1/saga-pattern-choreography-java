package com.learning.payment.service.common.service;

import com.learning.common.payload.inventory.model.InventoryPayload;
import com.learning.common.payload.payment.event.PaymentEventPayload;

import java.util.UUID;

public interface PaymentService {

    PaymentEventPayload paymentProcess(InventoryPayload inventory, UUID customerId);
}
