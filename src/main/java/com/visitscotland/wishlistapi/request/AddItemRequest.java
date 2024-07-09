package com.visitscotland.wishlistapi.request;

import com.visitscotland.wishlistapi.validation.ValidCategory;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.URL;

import java.time.LocalDateTime;
import java.util.Map;


public record AddItemRequest(
    @NotBlank String title,
    @NotBlank String description,
    @ValidCategory String category,
    @NotBlank @URL String image,
    @FutureOrPresent LocalDateTime eventDateTime,
    Map<String, String> metadata
) { }