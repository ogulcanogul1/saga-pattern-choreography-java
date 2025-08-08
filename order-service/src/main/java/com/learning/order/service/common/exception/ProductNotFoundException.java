package com.learning.order.service.common.exception;

public class ProductNotFoundException extends RuntimeException {
    private static final String MESSAGE = "Product not found";

    public ProductNotFoundException()
    {
        super(MESSAGE);
    }
}
