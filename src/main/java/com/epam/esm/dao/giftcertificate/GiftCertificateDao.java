package com.epam.esm.dao.giftcertificate;

import com.epam.esm.entity.GiftCertificate;
import com.epam.esm.dao.CommonDao;
import com.epam.esm.entity.Tag;

import java.util.List;

public interface GiftCertificateDao extends CommonDao<GiftCertificate, Integer> {
    void addGiftCertificatesTags(List<Tag> giftCertificatesTags, GiftCertificate giftCertificate);
}
