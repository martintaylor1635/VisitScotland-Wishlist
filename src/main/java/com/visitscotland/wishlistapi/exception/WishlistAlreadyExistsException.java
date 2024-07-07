package com.visitscotland.wishlistapi.exception;

import com.visitscotland.wishlistapi.exception.base.AlreadyExistsException;

public class WishlistAlreadyExistsException extends AlreadyExistsException {
    private static final String EXCEPTION_MESSAGE = "The wishlist with ID %s already exists";

    public WishlistAlreadyExistsException(String userId) {
        super(EXCEPTION_MESSAGE.formatted(userId));
    }
}