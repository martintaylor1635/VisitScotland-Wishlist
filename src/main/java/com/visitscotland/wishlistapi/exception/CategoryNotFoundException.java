package com.visitscotland.wishlistapi.exception;

import com.visitscotland.wishlistapi.exception.base.NotFoundException;

public class CategoryNotFoundException extends NotFoundException {
    public CategoryNotFoundException(String message) {
        super(message);
    }
}
