package com.visitscotland.wishlistapi.domain;

import java.net.URI;
import java.time.ZonedDateTime;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

public class Item {
    private final String id;
    private final String title;
    private final String description;
    private final Category category;
    private final URI image;
    private final ZonedDateTime eventDateTime;
    private final Map<String, Object> metadata;

    public Item(String id, String title, String description, Category category, URI image, ZonedDateTime eventDateTime, Map<String, Object> metadata) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.category = category;
        this.image = image;
        this.eventDateTime = eventDateTime;
        this.metadata = metadata;
    }

    // Getters
    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public Category getCategory() {
        return category;
    }

    public URI getImage() {
        return image;
    }

    public Optional<ZonedDateTime> getEventDateTime() {
        return Optional.ofNullable(eventDateTime);
    }

    // Equals and Hashcode
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Item item = (Item) o;
        return Objects.equals(id, item.id) && Objects.equals(title, item.title);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title);
    }
}