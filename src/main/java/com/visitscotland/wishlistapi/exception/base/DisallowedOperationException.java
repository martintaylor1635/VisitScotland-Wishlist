package com.visitscotland.wishlistapi.exception.base;

public abstract class DisallowedOperationException extends RuntimeException {
    public DisallowedOperationException(String message) {
        super(message);
    }
}