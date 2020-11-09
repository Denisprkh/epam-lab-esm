package com.epam.esm.service;

import com.epam.esm.dto.GiftCertificateDto;
import com.epam.esm.exception.ResourceAlreadyExistsException;
import com.epam.esm.exception.ResourceNotFoundException;

import java.util.List;
import java.util.Map;

/**
 * {@code GiftCertificate} service interface
 */
public interface GiftCertificateService {

    /**
     * Adds gift certificate with its tags using values from {@code GiftCertificateDto}.
     *
     * @param giftCertificateDto {@code GiftCertificateDto} to add.
     * @return GiftCertificateDto with its tags.
     * @throws ResourceAlreadyExistsException if certificate with the same name is already exists.
     */
    GiftCertificateDto addGiftCertificate(GiftCertificateDto giftCertificateDto);

    /**
     * Finds gift certificate by its id.
     *
     * @param id {@code GiftCertificate}'s id.
     * @return {@code GiftCertificateDto}.
     */
    GiftCertificateDto findGiftCertificateById(Integer id);

    /**
     * Deletes gift certificate by its id.
     *
     * @param id {@code GiftCertificate}'s id.
     * @return {@code true} if certificate is deleted.
     * @throws ResourceNotFoundException if certificate doesn't exist.
     */
    boolean deleteGiftCertificate(Integer id);

    /**
     * Updates gift certificate.
     *
     * @param giftCertificateDto {@code GiftCertificateDto} with updated values.
     * @param id                 {@code GiftCertificates}'s id to update.
     * @return {@code GiftCertificateDto} with updated values.
     * @throws ResourceAlreadyExistsException if certificate with updated name is already exists.
     */
    GiftCertificateDto updateGiftCertificate(GiftCertificateDto giftCertificateDto, Integer id);

    /**
     * Finds all gift certificates by specific query.
     *
     * @param params Params to build query for search.
     * @return List of {@code GiftCertificateDto} satisfying to params.
     */
    List<GiftCertificateDto> findGiftCertificates(Map<String, String> params);

}
