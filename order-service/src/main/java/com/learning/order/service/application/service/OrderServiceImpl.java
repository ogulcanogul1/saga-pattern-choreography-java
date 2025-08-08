package com.learning.order.service.application.service;

import com.learning.common.event.OrderStatus;
import com.learning.common.payload.order.event.OrderCancelledEventPayload;
import com.learning.common.payload.order.event.OrderCompletedEventPayload;
import com.learning.common.payload.order.event.OrderCreatedEventPayload;
import com.learning.common.payload.order.model.OrderPayload;
import com.learning.common.payload.payment.event.PaymentProcessedEventPayload;
import com.learning.common.publisher.event.order.OrderCancelledEventPublisher;
import com.learning.common.publisher.event.order.OrderCompletedEventPublisher;
import com.learning.common.publisher.event.order.OrderCreatedEventPublisher;
import com.learning.order.service.application.entity.Order;
import com.learning.order.service.application.mapper.OrderMapper;
import com.learning.order.service.application.repository.OrderRepository;
import com.learning.order.service.common.dto.CreateOrderRequest;
import com.learning.order.service.common.dto.CreateOrderResponse;
import com.learning.order.service.common.dto.OrderItemDto;
import com.learning.order.service.common.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@Slf4j
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final OrderMapper orderMapper;

    private final OrderCreatedEventPublisher orderCreatedEventPublisher;

    private final OrderCancelledEventPublisher orderCancelledEventPublisher;

    private final OrderCompletedEventPublisher orderCompletedEventPublisher;

    @Autowired
    public OrderServiceImpl(OrderRepository orderRepository,
                            OrderMapper orderMapper, OrderCreatedEventPublisher orderCreatedEventPublisher, OrderCancelledEventPublisher orderCancelledEventPublisher, OrderCompletedEventPublisher orderCompletedEventPublisher) {
        this.orderRepository = orderRepository;
        this.orderMapper = orderMapper;
        this.orderCreatedEventPublisher = orderCreatedEventPublisher;
        this.orderCancelledEventPublisher = orderCancelledEventPublisher;
        this.orderCompletedEventPublisher = orderCompletedEventPublisher;
    }

    @Override
    public CreateOrderResponse createOrder(CreateOrderRequest createOrderRequest) {

        UUID orderId = UUID.randomUUID();

        createOrderRequest.getOrderItems()
                .forEach(orderItemDto -> orderItemDto.setOrderId(orderId));

        boolean control = checkOrder(createOrderRequest.getOrderItems());

        Order order = orderMapper.createOrderRequestToOrder(createOrderRequest,orderId);

        if (!control) {
            order.setOrderStatus(OrderStatus.CANCELLED);
        }else{
            order.setOrderStatus(OrderStatus.PENDING);
        }

        order = orderRepository.save(order);


        CreateOrderResponse response = orderMapper.orderToCreateOrderResponse(order);

        OrderPayload orderPayload = orderMapper.orderToOrderPayload(order);
        OrderCreatedEventPayload orderCreatedEvent = new OrderCreatedEventPayload(orderPayload);


        orderCreatedEventPublisher.publish(orderCreatedEvent,orderPayload.getOrderId().toString());


        return response;
    }

    @Override
    public OrderCancelledEventPayload rollback(UUID orderId) {

        Optional<Order> order =  orderRepository.findById(orderId);

        if (!order.isPresent()) {
            log.error("Order not found with id: {}", orderId);
        }
        else{
            order.get().setOrderStatus(OrderStatus.CANCELLED);
            orderRepository.save(order.get());
        }
        OrderCancelledEventPayload orderCancelledEventPayload = new OrderCancelledEventPayload(orderId);

        log.info("Order cancelled with id: {}", orderId);

        orderCancelledEventPublisher.publish(orderCancelledEventPayload, orderId.toString());

        return orderCancelledEventPayload;
    }

    @Override
    public OrderCompletedEventPayload OrderCompletedEventProcess(PaymentProcessedEventPayload payload) {

        Order order =  orderRepository.findById(payload.getOrderId())
                .orElseThrow(() -> new RuntimeException("Order not found with id: " + payload.getOrderId()));



        order.setOrderStatus(OrderStatus.COMPLETED);

        orderRepository.save(order);


        order = orderRepository.findByIdWithItems(payload.getOrderId())
                .orElseThrow(() -> new RuntimeException("Order not found with id: " + payload.getOrderId()));




        OrderPayload orderPayload = orderMapper.orderToOrderPayload(order);

        OrderCompletedEventPayload orderCompletedEventPayload = new OrderCompletedEventPayload(orderPayload);

        orderCompletedEventPublisher.publish(orderCompletedEventPayload);

        return orderCompletedEventPayload;

    }


    private boolean checkOrder(List<OrderItemDto> orderItems) {

        boolean control = true;
        for (OrderItemDto orderItemDto : orderItems) {

            if(orderItemDto.getProductId() == null || orderItemDto.getPrice() < 0 || orderItemDto.getQuantity() < 0){
                control = false;
                break;
            }

        }

        return control;
    }
}
