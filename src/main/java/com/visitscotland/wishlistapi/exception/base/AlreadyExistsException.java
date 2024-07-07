package com.visitscotland.wishlistapi.exception.base;

public abstract class AlreadyExistsException extends RuntimeException {
    public AlreadyExistsException(String message) {
        super(message);
    }
}