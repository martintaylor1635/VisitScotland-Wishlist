package com.visitscotland.wishlistapi.repository;

import com.visitscotland.wishlistapi.domain.Wishlist;

import org.springframework.stereotype.Repository;

import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class WishlistRepository implements CrudRepository<Wishlist, String> {
    private final Map<String, Wishlist> wishlists;

    public WishlistRepository() {
        this.wishlists = new ConcurrentHashMap<>();
    }

    @Override
    public void save(Wishlist wishlist) {
        final String userId = wishlist.getUser().getId();
        wishlists.put(userId, wishlist);
    }

    @Override
    public Optional<Wishlist> findById(String userId) {
        return Optional.ofNullable(wishlists.get(userId));
    }

    @Override
    public Optional<Wishlist> deleteById(String userId) {
        return Optional.ofNullable(wishlists.remove(userId));
    }

    @Override
    public boolean existsById(String userId) {
        return wishlists.containsKey(userId);
    }
}