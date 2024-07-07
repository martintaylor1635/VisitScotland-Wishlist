package com.visitscotland.wishlistapi.exception.base;

public abstract class NotFoundException extends RuntimeException {
    public NotFoundException(String message) {
        super(message);
    }
}