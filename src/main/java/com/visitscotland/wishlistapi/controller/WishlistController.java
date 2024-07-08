package com.visitscotland.wishlistapi.controller;

import com.visitscotland.wishlistapi.domain.Wishlist;
import com.visitscotland.wishlistapi.request.AddItemRequest;
import com.visitscotland.wishlistapi.request.CreateWishlistRequest;
import com.visitscotland.wishlistapi.response.FilteredWishlistResponse;
import com.visitscotland.wishlistapi.service.WishlistService;

import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/wishlist")
class WishlistController {
    private final WishlistService wishlistService;

    public WishlistController(WishlistService wishlistService) {
        this.wishlistService = wishlistService;
    }

    @PostMapping
    private HttpEntity<Wishlist> createWishlist(@RequestBody CreateWishlistRequest request) {
        return ResponseEntity
            .created(wishlistService.createWishlist(request))
            .build();
    }

    @GetMapping("/{userId}")
    private HttpEntity<Wishlist> findWishlistByUserId(@PathVariable String userId) {
        return ResponseEntity
            .ok(wishlistService.getWishlistByUserId(userId));
    }

    @GetMapping("/{userId}/{category}")
    private HttpEntity<FilteredWishlistResponse> findWishlistAndFilterItemsByCategory(@PathVariable String userId, @PathVariable String category) {
        return ResponseEntity
            .ok(wishlistService.getWishlistAndFilterItemsByCategory(userId, category));
    }

    @DeleteMapping("/{userId}")
    private HttpEntity<Void> deleteWishlistByUserId(@PathVariable String userId) {
        wishlistService.deleteWishlistByUserId(userId);

        return ResponseEntity
            .noContent()
            .build();
    }

    @PostMapping("/{userId}/item")
    private HttpEntity<Wishlist> addItemToWishlist(@PathVariable String userId, @RequestBody AddItemRequest request) {
        wishlistService.addItemToWishlist(userId, request);

        return ResponseEntity
            .ok()
            .build();
    }

    @DeleteMapping("/{userId}/item/{itemId}")
    private HttpEntity<Void> deleteItemFromWishlist(@PathVariable String itemId, @PathVariable String userId) {
        wishlistService.deleteItemFromWishlist(userId, itemId);

        return ResponseEntity
            .noContent()
            .build();
    }
}