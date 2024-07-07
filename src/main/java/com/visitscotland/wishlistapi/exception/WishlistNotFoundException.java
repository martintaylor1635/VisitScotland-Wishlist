package com.visitscotland.wishlistapi.exception;

import com.visitscotland.wishlistapi.exception.base.NotFoundException;

public class WishlistNotFoundException extends NotFoundException {
    private static final String EXCEPTION_MESSAGE = "The wishlist for user ID %s could not be found";

    public WishlistNotFoundException(String userId) {
        super(EXCEPTION_MESSAGE.formatted(userId));
    }
}
