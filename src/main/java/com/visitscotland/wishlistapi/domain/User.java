package com.visitscotland.wishlistapi.domain;

import java.util.UUID;

public class User {
    private final UUID id;

    public UUID getId() {
        return id;
    }

    public User(UUID id) {
        this.id = id;
    }
}