package com.learning.order.service.common.exception;

public class InvalidQuantityException extends RuntimeException {
    private static final String MESSAGE = "Invalid quantity for the product";

    public InvalidQuantityException() {
        super(MESSAGE);
    }
}
