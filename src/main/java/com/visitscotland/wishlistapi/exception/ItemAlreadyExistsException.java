package com.visitscotland.wishlistapi.exception;

import com.visitscotland.wishlistapi.exception.base.AlreadyExistsException;

public class ItemAlreadyExistsException extends AlreadyExistsException {
    private static final String EXCEPTION_MESSAGE = "The item with ID %s already exists";

    public ItemAlreadyExistsException(String itemId) {
        super(EXCEPTION_MESSAGE.formatted(itemId));
    }
}