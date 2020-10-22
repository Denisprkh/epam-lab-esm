package com.epam.esm.repository;

public interface Repository<T, K> {

    T create(T t);

    T findById(K id);

    void delete(T t);

    void update(T t);

}
