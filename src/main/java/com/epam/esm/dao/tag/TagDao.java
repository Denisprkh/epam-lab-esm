package com.epam.esm.dao.tag;

import com.epam.esm.entity.Tag;
import com.epam.esm.dao.CommonDao;
import com.epam.esm.exception.ResourceNotFoundException;

import java.util.List;
import java.util.Optional;

/**
 * {@code Tag} dao interface
 */
public interface TagDao extends CommonDao<Tag, Integer> {

    /**
     * Finds tag by its name in datasource.
     *
     * @param tagName {@code Tag}'s name.
     * @return {@code Optional<Tag>}.
     */
    Optional<Tag> findTagByName(String tagName);

    /**
     * Finds tags belonged to gift certificate by its id in datasource.
     *
     * @param giftCertificateId {@code GiftCertificate}'s id.
     * @return List of {@code Tag}.
     * @throws ResourceNotFoundException if the tag doesn't exist
     */
    List<Tag> findAllGiftCertificatesTagsById(Integer giftCertificateId);

    /**
     * Deletes tag belonged to gift certificate in datasource.
     *
     * @param tagId {@code Tag}'s id.
     * @return {@code true} if {@code Tag} is deleted from gift certificate.
     */
    boolean deleteGiftCertificateTag(Integer tagId);
}
