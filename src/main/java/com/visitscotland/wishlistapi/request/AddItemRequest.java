package com.visitscotland.wishlistapi.request;

import com.visitscotland.wishlistapi.validation.ValidCategory;

import java.net.URI;
import java.time.ZonedDateTime;
import java.util.Map;

public record AddItemRequest(
    String title,
    String description,
    @ValidCategory
    String category,
    URI image,
    ZonedDateTime eventDateTime,
    Map<String, Object> metadata
) { }