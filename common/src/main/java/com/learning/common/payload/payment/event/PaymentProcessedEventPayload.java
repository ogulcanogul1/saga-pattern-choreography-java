package com.learning.common.payload.payment.event;

import com.learning.common.payload.payment.model.CustomerPaymentPayload;
import com.learning.common.payload.payment.model.PaymentPayload;
import lombok.*;

import java.util.UUID;

@Getter @Setter @Builder @NoArgsConstructor @AllArgsConstructor
public class PaymentProcessedEventPayload implements PaymentEventPayload {
    private PaymentPayload paymentPayload;
    private CustomerPaymentPayload customerPaymentPayload;
    private UUID orderId;

}
