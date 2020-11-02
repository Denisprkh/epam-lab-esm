package com.epam.esm.service;

import com.epam.esm.dto.GiftCertificateDto;

import java.util.List;
import java.util.Map;

public interface GiftCertificateService {

    GiftCertificateDto addGiftCertificate(GiftCertificateDto giftCertificateDto);

    GiftCertificateDto findGiftCertificateById(Integer id);

    boolean deleteGiftCertificate(Integer id);

    GiftCertificateDto updateGiftCertificate(GiftCertificateDto giftCertificateDto);

    List<GiftCertificateDto> findAllGiftCertificates();

    List<GiftCertificateDto> findGiftCertificates(Map<String, String> params);

}
