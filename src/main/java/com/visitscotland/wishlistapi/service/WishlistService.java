package com.visitscotland.wishlistapi.service;

import com.visitscotland.wishlistapi.domain.Category;
import com.visitscotland.wishlistapi.domain.Item;
import com.visitscotland.wishlistapi.domain.User;
import com.visitscotland.wishlistapi.domain.Wishlist;
import com.visitscotland.wishlistapi.exception.EventDateDisallowedException;
import com.visitscotland.wishlistapi.exception.ItemAlreadyExistsException;
import com.visitscotland.wishlistapi.exception.ItemNotFoundException;
import com.visitscotland.wishlistapi.exception.WishlistAlreadyExistsException;
import com.visitscotland.wishlistapi.exception.WishlistNotFoundException;
import com.visitscotland.wishlistapi.repository.WishlistRepository;
import com.visitscotland.wishlistapi.request.AddItemRequest;
import com.visitscotland.wishlistapi.request.CreateWishlistRequest;
import com.visitscotland.wishlistapi.response.FilteredWishlistResponse;
import com.visitscotland.wishlistapi.utility.MappingUtility;
import com.visitscotland.wishlistapi.validation.ValidCategory;

import jakarta.validation.Valid;

import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.net.URI;
import java.util.Locale;
import java.util.Set;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@Service
@Validated
public class WishlistService {
    private static final String GET_WISHLIST_BY_ID_ENDPOINT = "http://localhost:8080/api/wishlist/%s";
    private final WishlistRepository wishlistRepository;

    public WishlistService(WishlistRepository wishlistRepository) {
        this.wishlistRepository = wishlistRepository;
    }

    public URI createWishlist(@Valid CreateWishlistRequest request) {
        final String userId = request.userId();

        if(wishlistRepository.existsById(userId)) {
            throw new WishlistAlreadyExistsException(userId);
        }

        final User user = new User(userId);
        wishlistRepository.save(new Wishlist(user));
        return URI.create(GET_WISHLIST_BY_ID_ENDPOINT.formatted(user.getId()));
    }

    public Wishlist getWishlistByUserId(String userId) {
        return wishlistRepository.findById(userId)
            .orElseThrow(() -> new WishlistNotFoundException(userId));
    }

    public FilteredWishlistResponse getWishlistAndFilterItemsByCategory(String userId, @ValidCategory String category) {
        final Wishlist foundWishlist = getWishlistByUserId(userId);
        final Category requiredCategory = Category.valueOf(category.toUpperCase(Locale.ROOT));
        final Set<Item> filteredItems = foundWishlist
            .getItems()
            .stream()
            .filter(item -> item.getCategory().equals(requiredCategory))
            .collect(Collectors.toSet());

        return new FilteredWishlistResponse(foundWishlist.getUser(), filteredItems);
    }

    public void deleteWishlistByUserId(String userId) {
        wishlistRepository.deleteById(userId)
            .orElseThrow(() -> new WishlistNotFoundException(userId));
    }

    public void addItemToWishlist(String userId, @Valid AddItemRequest addItemRequest) {
        final Wishlist foundWishlist = getWishlistByUserId(userId);
        final Predicate<Item> isEventDateDisallowed = item -> !item.getCategory().equals(Category.EVENT)
            && item.getEventDateTime().isPresent();
        final Item item = MappingUtility
            .getItemMapper()
            .map(addItemRequest);

        if(foundWishlist.itemExists(item)) {
            throw new ItemAlreadyExistsException(item.getTitle());
        }

        if(isEventDateDisallowed.test(item)) {
            throw new EventDateDisallowedException();
        }

        foundWishlist.addItem(item);
        wishlistRepository.save(foundWishlist);
    }

    public void deleteItemFromWishlist(String userId, String itemId) {
        final Wishlist foundWishlist = getWishlistByUserId(userId);
        final boolean isRemoved = foundWishlist.removeItemByItemId(itemId);

        if(!isRemoved) {
            throw new ItemNotFoundException(itemId);
        }

        wishlistRepository.save(foundWishlist);
    }
}