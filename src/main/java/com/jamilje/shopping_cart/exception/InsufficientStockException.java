package com.jamilje.shopping_cart.exception;

public class InsufficientStockException extends RuntimeException {
    public InsufficientStockException(){
        super("Insufficient stock.");
    }
}
