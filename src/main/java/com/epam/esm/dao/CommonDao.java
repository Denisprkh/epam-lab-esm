package com.epam.esm.dao;

import java.util.List;

public interface CommonDao<T, K> {

    T create(T t);

    T findById(K id);

    boolean delete(K id);

    T update(T t);

    List<T> findAll();

}
