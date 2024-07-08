package com.visitscotland.wishlistapi.request;

import org.hibernate.validator.constraints.UUID;

public record CreateWishlistRequest(
    @UUID(message = "The User ID must be a valid UUID Version 4", version = 4)
    String userId
) { }