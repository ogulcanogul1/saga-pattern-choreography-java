package com.learning.order.service.controller;

import com.learning.order.service.common.dto.CreateOrderRequest;
import com.learning.order.service.common.dto.CreateOrderResponse;
import com.learning.order.service.common.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/orders/api")
public class OrderController {

    private final OrderService orderService;

    @Autowired
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping
    public ResponseEntity<CreateOrderResponse> createOrder(@RequestBody CreateOrderRequest createOrderRequest){
        CreateOrderResponse response = orderService.createOrder(createOrderRequest);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
}
