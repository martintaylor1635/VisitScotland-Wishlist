package com.visitscotland.wishlistapi.utility;

/**
 * A simple functional interface for mapping between
 * two objects of different types.
 * @param <I> The INPUT type.
 * @param <O> The OUTPUT type.
 */
@FunctionalInterface
public interface Mapper<I, O> {
    O map(I input);
}