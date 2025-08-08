package com.learning.inventory.service.application.service;

import com.learning.common.event.InventoryStatus;
import com.learning.common.payload.inventory.event.InventoryDeclinedEventPayload;
import com.learning.common.payload.inventory.event.InventoryEventPayload;
import com.learning.common.payload.inventory.event.InventoryReservedEventPayload;
import com.learning.common.payload.inventory.event.InventoryRestoredEventPayload;
import com.learning.common.payload.inventory.model.InventoryPayload;
import com.learning.common.payload.order.model.OrderPayload;
import com.learning.common.payload.payment.event.PaymentFailedEventPayload;
import com.learning.common.publisher.event.inventory.InventoryDeclinedEventPublisher;
import com.learning.common.publisher.event.inventory.InventoryReservedEventPublisher;
import com.learning.common.publisher.event.inventory.InventoryRestoredEventPublisher;
import com.learning.inventory.service.application.entity.Inventory;
import com.learning.inventory.service.application.entity.model.OrderDto;
import com.learning.inventory.service.application.entity.model.OrderItemDto;
import com.learning.inventory.service.application.mapper.InventoryMapper;
import com.learning.inventory.service.application.repository.InventoryRepository;
import com.learning.inventory.service.common.service.InventoryService;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@Slf4j
public class InventoryServiceImpl implements InventoryService {

    private final InventoryRepository inventoryRepository;

    private final InventoryReservedEventPublisher inventoryReservedEventPublisher;
    private final InventoryDeclinedEventPublisher inventoryDeclinedEventPublisher;
    private final InventoryRestoredEventPublisher inventoryRestoredEventPublisher;

    private final InventoryMapper inventoryMapper;

    @Autowired
    public InventoryServiceImpl(InventoryRepository inventoryRepository, InventoryReservedEventPublisher inventoryReservedEventPublisher, InventoryDeclinedEventPublisher inventoryDeclinedEventPublisher, InventoryRestoredEventPublisher inventoryRestoredEventPublisher, InventoryMapper inventoryMapper) {
        this.inventoryRepository = inventoryRepository;

        this.inventoryReservedEventPublisher = inventoryReservedEventPublisher;
        this.inventoryDeclinedEventPublisher = inventoryDeclinedEventPublisher;
        this.inventoryRestoredEventPublisher = inventoryRestoredEventPublisher;
        this.inventoryMapper = inventoryMapper;
    }

    @Override
    @Transactional
    public InventoryEventPayload checkInventory(OrderPayload orderPayload, UUID orderId) {

           OrderDto order =  inventoryMapper.orderPayloadToOrderDto(orderPayload);

            boolean control =  checkProductAndStock(order.getOrderItems());

            if(!control){
                InventoryDeclinedEventPayload inventoryDeclinedEventPayload = new InventoryDeclinedEventPayload(orderId);
                inventoryDeclinedEventPublisher.publish(inventoryDeclinedEventPayload,orderId.toString());
                return inventoryDeclinedEventPayload;
            }
            else {
                for (OrderItemDto orderItemDto : order.getOrderItems()) {
                    Inventory inventory = inventoryRepository.findInventoryByProductId(orderItemDto.getProductId());
                    inventory.setStock(inventory.getStock() - orderItemDto.getQuantity());
                    inventoryRepository.save(inventory);
                }
            }

            OrderPayload payloadOrder = inventoryMapper.orderDtoToOrderPayload(order);
            InventoryPayload inventoryPayload = new InventoryPayload(payloadOrder, InventoryStatus.SUCESSFUL);
            InventoryReservedEventPayload reservedEventPayload = new InventoryReservedEventPayload(inventoryPayload ,orderPayload.getCustomerId());
            inventoryReservedEventPublisher.publish(reservedEventPayload, orderId.toString());

            return reservedEventPayload;
    }

    @Override
    @Transactional
    public InventoryRestoredEventPayload processInventoryRestoration(PaymentFailedEventPayload eventPayload) {

        List<OrderItemDto> dtos = inventoryMapper.orderItemPayloadToOrderItemDto(eventPayload.getOrderItemPayload());

        for (OrderItemDto orderItemDto : dtos){
            UUID productId = orderItemDto.getProductId();
            Inventory inventory = inventoryRepository.findInventoryByProductId(productId);

            if (inventory == null) {
                log.error("Product not found! Stock restore failed for product ID: {}\n {}", productId, eventPayload);
                continue;
            }

            inventory.setStock(inventory.getStock() + orderItemDto.getQuantity());
            inventoryRepository.save(inventory);
        }


        InventoryRestoredEventPayload inventoryRestoredEventPayload = new InventoryRestoredEventPayload(eventPayload.getOrderId());
        inventoryRestoredEventPublisher.publish(inventoryRestoredEventPayload, eventPayload.getOrderId().toString());

        return inventoryRestoredEventPayload;
    }

    private boolean checkProductAndStock(List<OrderItemDto> orderItemDtos) {

        for (OrderItemDto orderItemDto : orderItemDtos) {
            Inventory inventory = inventoryRepository.findInventoryByProductId(orderItemDto.getProductId());

            if(inventory == null) {
                return false;
            }

            if (inventory.getStock() < orderItemDto.getQuantity()) {
                return false;
            }
        }

        return true;
    }
}
