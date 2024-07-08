package com.visitscotland.wishlistapi.response;

import com.visitscotland.wishlistapi.domain.Item;
import com.visitscotland.wishlistapi.domain.User;

import java.util.Set;

public record FilteredWishlistResponse (
    User user,
    Set<Item> items
) { }