package com.learning.payment.service.application.service;

import com.learning.common.event.PaymentStatus;
import com.learning.common.payload.inventory.model.InventoryPayload;
import com.learning.common.payload.payment.event.PaymentEventPayload;
import com.learning.common.payload.payment.event.PaymentProcessedEventPayload;
import com.learning.common.payload.payment.model.CustomerPaymentPayload;
import com.learning.common.payload.payment.model.PaymentPayload;
import com.learning.common.publisher.event.payment.PaymentProcessEventPublisher;
import com.learning.payment.service.application.entity.Customer;
import com.learning.payment.service.application.entity.model.OrderDto;
import com.learning.payment.service.application.entity.model.OrderItemDto;
import com.learning.payment.service.application.mapper.PaymentMapper;
import com.learning.payment.service.application.repository.CustomerRepository;
import com.learning.payment.service.common.service.PaymentService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@Transactional
public class PaymentServiceImpl implements PaymentService {

    private final CustomerRepository customerRepository;

    private final PaymentMapper paymentMapper;

    private final PaymentProcessEventPublisher paymentProcessEventPublisher;

    public PaymentServiceImpl(CustomerRepository customerRepository, PaymentMapper paymentMapper, PaymentProcessEventPublisher paymentProcessEventPublisher) {
        this.customerRepository = customerRepository;
        this.paymentMapper = paymentMapper;
        this.paymentProcessEventPublisher = paymentProcessEventPublisher;
    }

    @Override
    public PaymentEventPayload paymentProcess(InventoryPayload inventory, UUID customerId) {
        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(() -> new RuntimeException("Customer not found with id: " + customerId));

        double oldPrice =  customer.getBalance();

        checkPaymentStatus(customer , inventory);

        PaymentPayload paymentPayload = new PaymentPayload(inventory, PaymentStatus.PAID);

        CustomerPaymentPayload customerPaymentPayload = new CustomerPaymentPayload(customerId,
                customer.getName(),
                customer.getEmail(),
                customer.getPhoneNumber(),
                oldPrice,
                customer.getBalance());

        PaymentProcessedEventPayload payload = new PaymentProcessedEventPayload(paymentPayload,customerPaymentPayload,
                inventory.getOrder().getOrderId());

        paymentProcessEventPublisher.publish(payload,customerId.toString());

        return payload;

    }

    private void checkPaymentStatus(Customer customer, InventoryPayload inventory) {

        OrderDto orderDto = paymentMapper.orderPayloadToOrderDto(inventory.getOrder());

        double totalPrice = orderDto.getTotalPrice();
        double checkTotalPrice = 0;
        for (OrderItemDto item : orderDto.getOrderItems()){
            checkTotalPrice += (item.getPrice() * item.getQuantity());
        }

        if (checkTotalPrice != totalPrice) {
            throw new RuntimeException("Total price mismatch. Expected: " + totalPrice + ", Calculated: " + checkTotalPrice);
        }

        if(customer.getBalance() > totalPrice) {
            customer.setBalance(customer.getBalance() - totalPrice);
            customerRepository.save(customer);

        } else {
            throw new RuntimeException("Insufficient balance for customer: " + customer.getId());
        }

    }
}
