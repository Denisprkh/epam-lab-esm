package com.epam.esm.service;

import com.epam.esm.entity.Tag;
import com.epam.esm.exception.ResourceAlreadyExistsException;
import com.epam.esm.exception.ResourceNotFoundException;

import java.util.List;
import java.util.Optional;

/**
 * {@code Tag} service interface
 */
public interface TagService {

    /**
     * Creates tag.
     *
     * @param tag {@code Tag} for creation.
     * @return Created {@code Tag}.
     * @throws ResourceAlreadyExistsException if tag with the same name is already exists.
     */
    Tag createTag(Tag tag);

    /**
     * Finds tag by its id.
     *
     * @param id {@code Tag}'s id.
     * @return Found {@code Tag}.
     */
    Tag findTagById(Integer id);

    /**
     * Deletes tag by id.
     *
     * @param id {@code Tag}'s id.
     * @return {@code true} if {@code Tag} is deleted.
     * @throws ResourceNotFoundException if tag doesn't exist.
     */
    boolean deleteTag(Integer id);

    /**
     * Finds all existing tags.
     *
     * @return List of {@code Tag}.
     */
    List<Tag> findAllTags();

    /**
     * Finds tag by name.
     *
     * @param tagName {@code Tag}'s name.
     * @return {@code Optional<Tag>}
     */
    Optional<Tag> findTagByName(String tagName);

    /**
     * Finds all tags belong to gift certificate by its id.
     *
     * @param giftCertificatesId {@code GiftCertificate}'s id.
     * @return List of {@code Tag}.
     */
    List<Tag> findGiftCertificatesTags(Integer giftCertificatesId);

}
