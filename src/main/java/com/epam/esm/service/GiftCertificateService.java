package com.epam.esm.service;

import com.epam.esm.entity.GiftCertificate;

import java.util.List;

public interface GiftCertificateService {

    GiftCertificate addGiftCertificateAndItsTags(GiftCertificate giftCertificate);

    GiftCertificate findGiftCertificateById(Integer id);

    void deleteGiftCertificate(Integer id);

    GiftCertificate updateGiftCertificate(GiftCertificate giftCertificate);

    List<GiftCertificate> findAllGiftCertificates();
}
