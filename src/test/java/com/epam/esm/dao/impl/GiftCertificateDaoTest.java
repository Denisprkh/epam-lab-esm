package com.epam.esm.dao.impl;

import com.epam.esm.config.TestConfig;
import com.epam.esm.dao.giftcertificate.GiftCertificateDao;
import com.epam.esm.entity.GiftCertificate;
import com.epam.esm.exception.ResourceNotFoundException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;

@Component
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {TestConfig.class})
public class GiftCertificateDaoTest {

    @Qualifier("giftCertificateDao")
    @Autowired
    private GiftCertificateDao giftCertificateDao;

    @Test
    void findByIdTest() {
        int expectedId = 1;

        GiftCertificate resultGiftCertificate = giftCertificateDao.findById(1);

        assertEquals(expectedId, resultGiftCertificate.getId());
    }

    @Test
    void findAllTest() {
        int expectedListSize = 3;

        List<GiftCertificate> resultGiftCertificateList = giftCertificateDao.findAll();

        assertEquals(expectedListSize, resultGiftCertificateList.size());
    }

    @Test
    void createTest() {
        GiftCertificate giftCertificateForCreation = new GiftCertificate();
        giftCertificateForCreation.setName("Name");
        giftCertificateForCreation.setDurationInDays(5);
        giftCertificateForCreation.setPrice(new BigDecimal("123"));
        giftCertificateForCreation.setCreateDate(Timestamp.valueOf("2020-11-03 16:27:26.0"));
        giftCertificateForCreation.setLastUpdateDate(Timestamp.valueOf("2020-11-03 16:27:26.0"));
        giftCertificateForCreation.setDescription("Good");
        int expectedId = 4;

        GiftCertificate resultCreatedGiftCertificate = giftCertificateDao.create(giftCertificateForCreation);

        assertEquals(expectedId, resultCreatedGiftCertificate.getId());
    }

    @Test
    void updateTest() {
        GiftCertificate giftCertificateForUpdate = new GiftCertificate();
        giftCertificateForUpdate.setId(1);
        giftCertificateForUpdate.setName("newName");
        giftCertificateForUpdate.setDurationInDays(5);
        giftCertificateForUpdate.setPrice(new BigDecimal("123"));
        giftCertificateForUpdate.setCreateDate(Timestamp.valueOf("2020-11-03 16:27:26.0"));
        giftCertificateForUpdate.setLastUpdateDate(Timestamp.valueOf("2020-11-03 16:27:26.0"));
        giftCertificateForUpdate.setDescription("Good");
        String expectedName = "newName";

        GiftCertificate resultUpdatedGiftCertificate = giftCertificateDao.update(giftCertificateForUpdate);

        assertEquals(expectedName, resultUpdatedGiftCertificate.getName());
    }

    @Test
    void deleteTest() {
        assertEquals(Boolean.TRUE, giftCertificateDao.delete(1));
    }

    @Test
    void findByNameTest() {
        int expectedCertificateId = 1;

        GiftCertificate resultFoundByNameCertificate = giftCertificateDao.findByName("Gym").get();

        assertEquals(expectedCertificateId, resultFoundByNameCertificate.getId());
    }

    @Test
    void findGiftCertificatesByParametersTest() {
        String searchQuery = "";
        int expectedListSize = 3;

        List<GiftCertificate> resultGiftCertificateList = giftCertificateDao.findGiftCertificatesByParameters(searchQuery);

        assertEquals(expectedListSize, resultGiftCertificateList.size());
    }

    @Test
    void createFindByIdTestShouldThrowException() {
        assertThrows(ResourceNotFoundException.class, () -> giftCertificateDao.findById(20));
    }

    @Test
    void deleteGiftCertificatesTagsTest() {
        assertEquals(Boolean.TRUE, giftCertificateDao.deleteGiftCertificatesTags(1));
    }
}
