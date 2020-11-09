package com.epam.esm.dao;

import java.util.List;

/**
 * Common dao interface.
 *
 * @param <T> Some entity to work with.
 * @param <K> Entities id.
 */
public interface CommonDao<T, K> {

    /**
     * Creates object in datasource.
     *
     * @param t object to create.
     * @return created object.
     */
    T create(T t);

    /**
     * Returns resource found by its id.
     *
     * @param id resources id.
     * @return found resource.
     */
    T findById(K id);

    /**
     * Deletes resource in datasource by its id.
     *
     * @param id Resources id.
     * @return {@code true} if resource is deleted.
     */
    boolean delete(K id);

    /**
     * Updates object in datasource.
     *
     * @param t object with updated values.
     * @return updated object.
     */
    T update(T t);

    /**
     * Returns all objects stored in datasource.
     *
     * @return List of resources.
     */
    List<T> findAll();

}
