package com.epam.esm.repository;

import java.util.List;

public interface Repository<T, K> {

    T create(T t);

    T findById(K id);

    boolean delete(T t);

    void update(T t);

    List<T> findAll();

}
