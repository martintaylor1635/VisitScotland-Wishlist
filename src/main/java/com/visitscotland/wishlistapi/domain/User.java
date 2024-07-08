package com.visitscotland.wishlistapi.domain;

public class User {
    private final String id;

    public User(String id) {
        this.id = id;
    }

    // Getters
    public String getId() {
        return id;
    }
}