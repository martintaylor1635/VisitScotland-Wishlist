package com.visitscotland.wishlistapi.utility;

import com.visitscotland.wishlistapi.domain.Category;
import com.visitscotland.wishlistapi.domain.Item;
import com.visitscotland.wishlistapi.request.AddItemRequest;

import java.util.UUID;

public final class MappingUtility {
    private MappingUtility() { }

    public static Mapper<AddItemRequest, Item> getItemMapper() {
        return request -> new Item(
            UUID.randomUUID().toString(),
            request.title(),
            request.description(),
            Category.valueOf(request.category()),
            request.image(),
            request.eventDateTime(),
            request.metadata()
        );
    }
}