package com.visitscotland.wishlistapi.utility;

import com.visitscotland.wishlistapi.domain.Category;
import com.visitscotland.wishlistapi.domain.Item;
import com.visitscotland.wishlistapi.request.AddItemRequest;

import java.net.URI;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.Objects;
import java.util.UUID;

public final class MappingUtility {
    private MappingUtility() { }

    public static Mapper<AddItemRequest, Item> getItemMapper() {
        return request -> new Item(
            UUID.randomUUID().toString(),
            request.title(),
            request.description(),
            parseCategory(request.category()),
            parseUri(request.image()),
            formatDate(request.eventDateTime()),
            request.metadata()
        );
    }

    private static String formatDate(LocalDateTime dateTime) {
        if(Objects.isNull(dateTime)) {
            return null;
        }

        return dateTime.format(DateTimeFormatter.ISO_LOCAL_DATE_TIME);
    }

    private static URI parseUri(String uri) {
        return URI.create(uri);
    }

    private static Category parseCategory(String category) {
        return Category.valueOf(category.toUpperCase(Locale.ROOT));
    }
}