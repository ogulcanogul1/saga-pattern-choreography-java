package com.learning.inventory.service.application.event.processor;

import com.learning.common.payload.inventory.event.InventoryEventPayload;
import com.learning.common.payload.order.event.OrderCreatedEventPayload;
import com.learning.common.processor.event.OrderCreatedEventProcessor;
import com.learning.inventory.service.application.entity.model.OrderItemDto;
import com.learning.inventory.service.application.mapper.InventoryMapper;
import com.learning.inventory.service.common.service.InventoryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Slf4j
public class OrderCreatedEventProcessorImpl implements OrderCreatedEventProcessor {

    private InventoryMapper inventoryMapper;

    private InventoryService inventoryService;

    @Autowired
    public OrderCreatedEventProcessorImpl(InventoryService inventoryService,InventoryMapper inventoryMapper) {
        this.inventoryService = inventoryService;
        this.inventoryMapper = inventoryMapper;
    }


    @Override
    public InventoryEventPayload process(OrderCreatedEventPayload event) {

        List<OrderItemDto> orderItemDtos = inventoryMapper.orderItemPayloadToOrderItemDto(event.orderPayload().getOrderItems());

        InventoryEventPayload eventToSend =  inventoryService.checkInventory(event.orderPayload(),event.orderPayload().getOrderId());


        log.info("OrderCreatedEventProcessorImpl: Order created event processed with payload: {}", eventToSend);
        return eventToSend;
    }
}
