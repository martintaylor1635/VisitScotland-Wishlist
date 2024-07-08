package com.visitscotland.wishlistapi.repository;

import java.util.Optional;

interface CrudRepository<T, ID> {
    void save(T t);
    Optional<T> findById(ID id);
    Optional<T> deleteById(ID id);
    boolean existsById(ID id);
}