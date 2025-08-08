package com.learning.order.service.common.exception;

public class InvalidPriceException extends RuntimeException {
    private static final String MESSAGE = "Invalid price for the product";
    public InvalidPriceException() {
        super(MESSAGE);
    }
}
