package com.learning.common.payload.payment.model;

import java.util.UUID;

public record CustomerPaymentPayload(UUID id, String name, String email, String phoneNumber, Double oldBalance, Double balance) {

}
