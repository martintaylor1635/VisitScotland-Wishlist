package com.visitscotland.wishlistapi.exception;

import com.visitscotland.wishlistapi.exception.base.NotFoundException;

public class ItemNotFoundException extends NotFoundException {
    private static final String EXCEPTION_MESSAGE = "The item with ID %s could not be found";

    public ItemNotFoundException(String itemId) {
        super(EXCEPTION_MESSAGE.formatted(itemId));
    }
}