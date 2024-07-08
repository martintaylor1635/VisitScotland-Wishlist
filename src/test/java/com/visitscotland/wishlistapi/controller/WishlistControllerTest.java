package com.visitscotland.wishlistapi.controller;

import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class WishlistControllerTest {
    @Autowired
    private MockMvc mockMvc;

    private static final String CREATE_WISHLIST_REQUEST_BODY = """
            {
                "userId": "%s"
            }
        """;

    @Test
    void Given_ValidCreateWishlistRequest_Expect_WishlistCreatedAndResponse201() throws Exception {
        final String userId = "d9bf51d2-d2c6-49c6-af0b-a88882a1d304";
        final String locationHeader = "http://localhost:8080/api/wishlist/%s".formatted(userId);

        mockMvc.perform(MockMvcRequestBuilders
                .post("/api/wishlist")
                .contentType(MediaType.APPLICATION_JSON)
                .content(CREATE_WISHLIST_REQUEST_BODY.formatted(userId)))
            .andExpect(status().isCreated())
            .andExpect(MockMvcResultMatchers.header().string("Location", locationHeader));
    }

    @Test
    void Given_CreateWishlistRequestWithInvalidUuid_Expect_WishlistNotCreatedAndResponse400() throws Exception {
        final String userId = "invalid-uuid";
        final String problemDetail = """
            {
                "type": "about:blank",
                "title": "Bad Request",
                "status": 400,
                "detail": "The User ID must be a valid UUID Version 4",
                "instance": "/api/wishlist"
            }
        """;

        mockMvc.perform(MockMvcRequestBuilders
                .post("/api/wishlist")
                .contentType(MediaType.APPLICATION_JSON)
                .content(CREATE_WISHLIST_REQUEST_BODY.formatted(userId)))
            .andExpect(status().isBadRequest())
            .andExpect(content().contentType(MediaType.APPLICATION_PROBLEM_JSON))
            .andExpect(content().json(problemDetail));
    }

    @Test
    void Given_CreateWishlistRequestWhichAlreadyExists_Expect_WishlistNotToBeCreatedAndResponse409() throws Exception {
        final String userId = "c2f44840-0685-4601-93bb-44ef3c3545bb";
        final String locationHeader = "http://localhost:8080/api/wishlist/%s".formatted(userId);
        final String problemDetail = """
           {
                "type": "about:blank",
                "title": "Conflict",
                "status": 409,
                "detail": "The wishlist with ID c2f44840-0685-4601-93bb-44ef3c3545bb already exists",
                "instance": "/api/wishlist"
            }
           """;

        // First request: Create wishlist and expect 201 Created status
        mockMvc.perform(MockMvcRequestBuilders
                .post("/api/wishlist")
                .contentType(MediaType.APPLICATION_JSON)
                .content(CREATE_WISHLIST_REQUEST_BODY.formatted(userId)))
            .andExpect(status().isCreated())
            .andExpect(MockMvcResultMatchers.header().string("Location", locationHeader));

        // Second request: Attempt to create the same wishlist again and expect 409 Conflict status
        mockMvc.perform(MockMvcRequestBuilders
                .post("/api/wishlist")
                .contentType(MediaType.APPLICATION_JSON)
                .content(CREATE_WISHLIST_REQUEST_BODY.formatted(userId)))
            .andExpect(status().isConflict())
            .andExpect(content().contentType(MediaType.APPLICATION_PROBLEM_JSON))
            .andExpect(content().json(problemDetail));
    }
}