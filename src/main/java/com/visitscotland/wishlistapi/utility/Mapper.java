package com.visitscotland.wishlistapi.utility;

@FunctionalInterface
public interface Mapper<I, O> {
    O map(I input);
}