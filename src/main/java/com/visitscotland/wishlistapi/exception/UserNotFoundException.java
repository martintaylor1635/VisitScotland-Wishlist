package com.visitscotland.wishlistapi.exception;

import com.visitscotland.wishlistapi.exception.base.NotFoundException;

import java.util.UUID;

public class UserNotFoundException extends NotFoundException {
    private static final String EXCEPTION_MESSAGE = "The user with ID %s could not be found";

    public UserNotFoundException(UUID userId) {
        super(EXCEPTION_MESSAGE.formatted(userId));
    }
}
