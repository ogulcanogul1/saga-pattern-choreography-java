package com.learning.payment.service.application.aop;

import com.learning.common.payload.inventory.model.InventoryPayload;
import com.learning.common.payload.payment.event.PaymentFailedEventPayload;
import com.learning.common.publisher.event.payment.PaymentFailedEventPublisher;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Aspect
@Component
public class PaymentExceptionAspect {

    private final PaymentFailedEventPublisher paymentFailedEventEventPublisher;


    public PaymentExceptionAspect(PaymentFailedEventPublisher paymentFailedEventEventPublisher) {
        this.paymentFailedEventEventPublisher = paymentFailedEventEventPublisher;
    }

    @AfterThrowing(
            pointcut = "execution(* com.learning.payment.service.application.service.PaymentServiceImpl.paymentProcess(..)) && args(inventory,customerId)",
            throwing = "ex"
    )
    public void handleInventoryException(InventoryPayload inventory, UUID customerId, Throwable ex) {
        System.out.println("Inventory işlemi başarısız oldu: " + ex.getMessage());


        PaymentFailedEventPayload paymentFailedEventPayload = new PaymentFailedEventPayload(
                inventory.getOrder().getOrderId(),
                inventory.getOrder().getCustomerId(),
                inventory.getOrder().getOrderItems()
        );
        paymentFailedEventEventPublisher.publish(paymentFailedEventPayload);
    }
}

//args(orderItemDtos, orderId)


