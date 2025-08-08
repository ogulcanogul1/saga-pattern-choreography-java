package com.learning.inventory.service.application.aop;

import com.learning.common.event.InventoryStatus;
import com.learning.common.payload.inventory.event.InventoryDeclinedEventPayload;
import com.learning.common.publisher.EventPublisher;
import com.learning.common.publisher.event.inventory.InventoryDeclinedEventPublisher;
import com.learning.inventory.service.application.entity.model.OrderItemDto;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@Aspect
@Component
public class InventoryExceptionAspect {

    private final InventoryDeclinedEventPublisher inventoryDeclinedEventPublisher;


    public InventoryExceptionAspect(InventoryDeclinedEventPublisher inventoryDeclinedEventPublisher) {
        this.inventoryDeclinedEventPublisher = inventoryDeclinedEventPublisher;
    }

    @AfterThrowing(
            pointcut = "execution(* com.learning.inventory.service.application.service.InventoryServiceImpl.checkInventory(..)) && args(orderItemDtos, orderId)",
            throwing = "ex"
    )
    public void handleInventoryException(List<OrderItemDto> orderItemDtos, UUID orderId, Throwable ex) {
        System.out.println("Inventory işlemi başarısız oldu: " + ex.getMessage());

        InventoryDeclinedEventPayload payload = new InventoryDeclinedEventPayload(orderId);
        inventoryDeclinedEventPublisher.publish(payload, orderId.toString());
    }
}




