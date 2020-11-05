package com.epam.esm.dao.impl;

import com.epam.esm.config.TestConfig;
import com.epam.esm.dao.giftcertificate.GiftCertificateDao;
import com.epam.esm.entity.GiftCertificate;
import com.epam.esm.exception.ResourceNotFoundException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.math.BigDecimal;
import java.sql.Timestamp;

@Component
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {TestConfig.class})
public class GiftCertificateDaoTest {

    @Qualifier("getGiftCertificateDaoTest")
    @Autowired
    private GiftCertificateDao giftCertificateDao;

    @Test
    void findByIdTest() {
        int expectedId = 1;
        int resultId = giftCertificateDao.findById(1).getId();
        Assertions.assertEquals(expectedId, resultId);
    }

    @Test
    void findAllTest() {
        int expectedListSize = 3;
        int resultListSize = giftCertificateDao.findAll().size();
        Assertions.assertEquals(expectedListSize, resultListSize);
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
        int resultId = giftCertificateDao.create(giftCertificateForCreation).getId();
        Assertions.assertEquals(expectedId, resultId);
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
        String resultName = giftCertificateDao.update(giftCertificateForUpdate).getName();
        Assertions.assertEquals(expectedName, resultName);
    }

    @Test
    void deleteTest() {
        Assertions.assertEquals(Boolean.TRUE, giftCertificateDao.delete(1));
    }

    @Test
    void findByNameTest() {
        int expectedCertificateId = 1;
        int resultId = giftCertificateDao.findByName("Gym").get().getId();
        Assertions.assertEquals(expectedCertificateId, resultId);
    }

    @Test
    void findGiftCertificatesByParametersTest() {
        String searchQuery = "";
        int expectedListSize = 3;
        int resultListSize = giftCertificateDao.findGiftCertificatesByParameters(searchQuery).size();
        Assertions.assertEquals(expectedListSize, resultListSize);
    }

    @Test
    void createFindByIdTest_Should_Throw_Exception() {
        Assertions.assertThrows(ResourceNotFoundException.class, () -> giftCertificateDao.findById(20));
    }

    @Test
    void deleteGiftCertificatesTagsTest() {
        Assertions.assertEquals(Boolean.TRUE, giftCertificateDao.deleteGiftCertificatesTags(1));
    }
}
