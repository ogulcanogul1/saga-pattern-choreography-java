package com.learning.common.payload.payment.model;

import com.learning.common.event.PaymentStatus;
import com.learning.common.payload.inventory.model.InventoryPayload;
import lombok.*;


@Getter @Setter @Builder @NoArgsConstructor @AllArgsConstructor
public class PaymentPayload {

    private InventoryPayload inventoryPayload;

    private PaymentStatus paymentStatus;
}
