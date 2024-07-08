package com.visitscotland.wishlistapi.domain;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class Wishlist {
    private final User user;
    private final Set<Item> items;

    public Wishlist(User user) {
        this.user = Objects.requireNonNull(user);
        this.items = new HashSet<>();
    }

    public User getUser() {
        return user;
    }

    public Set<Item> getItems() {
        return items;
    }

    public void addItem(Item item) {
        items.add(
            Objects.requireNonNull(item)
        );
    }

    public boolean removeItemByItemId(String itemId) {
        return items.removeIf(item -> item.getId().equals(itemId));
    }

    public boolean itemExists(Item item) {
        return items.contains(item);
    }
}