package com.visitscotland.wishlistapi.exception;

import com.visitscotland.wishlistapi.exception.base.AlreadyExistsException;

public class ItemAlreadyExistsException extends AlreadyExistsException {
    private static final String EXCEPTION_MESSAGE = "The item with title '%s' already exists";

    public ItemAlreadyExistsException(String title) {
        super(EXCEPTION_MESSAGE.formatted(title));
    }
}