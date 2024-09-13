package com.jamilje.shopping_cart.exception;

public class UnableToSaveException extends RuntimeException {
    public UnableToSaveException() {
        super("Unable to save. Please contact support.");
    }
}
