package com.visitscotland.wishlistapi.exception;

import com.visitscotland.wishlistapi.exception.base.DisallowedOperationException;

public class EventDateDisallowedException extends DisallowedOperationException {
    private static final String EXCEPTION_MESSAGE = "For the event date time to be permitted, the category must be event";

    public EventDateDisallowedException() {
        super(EXCEPTION_MESSAGE);
    }
}