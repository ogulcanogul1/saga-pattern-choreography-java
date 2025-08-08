package com.learning.common.payload.payment.event;

import com.learning.common.payload.order.model.OrderItemPayload;
import lombok.*;

import java.util.List;
import java.util.UUID;

@Getter @Setter @Builder @NoArgsConstructor @AllArgsConstructor
public class PaymentFailedEventPayload implements PaymentEventPayload {

    private UUID orderId;
    private UUID customerId;

    private List<OrderItemPayload> orderItemPayload;
}
