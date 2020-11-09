package com.epam.esm.dao.giftcertificate;

import com.epam.esm.entity.GiftCertificate;
import com.epam.esm.dao.CommonDao;
import com.epam.esm.entity.Tag;
import com.epam.esm.exception.ResourceNotFoundException;

import java.util.List;
import java.util.Optional;

/**
 * {@code GiftCertificate} dao interface.
 */
public interface GiftCertificateDao extends CommonDao<GiftCertificate, Integer> {

    /**
     * Adds tags to gift certificate in datasource.
     *
     * @param giftCertificatesTags List of {@code Tag}.
     * @param giftCertificate      {@code GiftCertificate}.
     */
    void addGiftCertificatesTags(List<Tag> giftCertificatesTags, GiftCertificate giftCertificate);

    /**
     * Deletes all tags from gift certificate in datasource.
     *
     * @param giftCertificatesId {@code GiftCertificate}'s id.
     * @return {@code true} if tags are deleted.
     */
    boolean deleteGiftCertificatesTags(Integer giftCertificatesId);

    /**
     * Finds gift certificates by a specific query in datasource.
     *
     * @param condition Specific query.
     * @return List of {@code GiftCertificate}
     */
    List<GiftCertificate> findGiftCertificatesByParameters(String condition);

    /**
     * Finds gift certificate by name in datasource.
     *
     * @param name Gift certificates name.
     * @return {@code Optional<GiftCertificate>} found gift certificate.
     * @throws ResourceNotFoundException if no certificates were found
     */
    Optional<GiftCertificate> findByName(String name);
}
