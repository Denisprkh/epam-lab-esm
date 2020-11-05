package com.epam.esm.service.impl;

import com.epam.esm.dao.giftcertificate.GiftCertificateDao;
import com.epam.esm.dto.GiftCertificateDto;
import com.epam.esm.dto.mapper.impl.GiftCertificateDtoMapper;
import com.epam.esm.entity.GiftCertificate;
import com.epam.esm.exception.ResourceAlreadyExistsException;
import com.epam.esm.exception.ResourceNotFoundException;
import com.epam.esm.service.TagService;
import com.epam.esm.service.util.GiftCertificateSearchQueryBuilder;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class GiftCertificateServiceTest {

    @Mock
    private GiftCertificateDao giftCertificateDao;

    @Mock
    private TagService tagService;

    @Spy
    private GiftCertificateDtoMapper dtoMapper = new GiftCertificateDtoMapper();

    @Spy
    private GiftCertificateSearchQueryBuilder queryBuilder = new GiftCertificateSearchQueryBuilder();

    @InjectMocks
    private GiftCertificateServiceImpl giftCertificateService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void findGiftCertificateByIdTest() {
        GiftCertificate giftCertificate = new GiftCertificate();
        giftCertificate.setId(1);
        giftCertificate.setLastUpdateDate(Timestamp.valueOf("2020-11-03 16:27:26.0"));
        giftCertificate.setCreateDate(Timestamp.valueOf("2020-11-03 16:27:26.0"));
        GiftCertificateDto returnedDto = new GiftCertificateDto();
        returnedDto.setId(1);
        returnedDto.setLastUpdateDate("2020-11-03T13:27Z");
        returnedDto.setCreateDate("2020-11-03T13:27Z");
        returnedDto.setTags(Collections.emptyList());
        Mockito.when(giftCertificateDao.findById(1)).thenReturn(giftCertificate);
        GiftCertificateDto resultDto = giftCertificateService.findGiftCertificateById(1);
        Assertions.assertEquals(returnedDto, resultDto);
        Mockito.verify(tagService, Mockito.times(1)).findGiftCertificatesTags(1);
    }

    @Test
    void deleteGiftCertificateTest() {
        Mockito.when(giftCertificateDao.delete(1)).thenReturn(Boolean.TRUE);
        Mockito.when(giftCertificateDao.deleteGiftCertificatesTags(1)).thenReturn(Boolean.TRUE);
        Assertions.assertEquals(Boolean.TRUE, giftCertificateService.deleteGiftCertificate(1));
        Mockito.verify(giftCertificateDao, Mockito.times(1)).delete(1);
        Mockito.verify(giftCertificateDao, Mockito.times(1)).deleteGiftCertificatesTags(1);
    }

    @Test
    void findGiftCertificatesTest() {
        List<GiftCertificate> giftCertificateList = new ArrayList<>();
        GiftCertificate giftCertificate = new GiftCertificate();
        giftCertificate.setLastUpdateDate(Timestamp.valueOf("2020-11-03 16:27:26.0"));
        giftCertificate.setCreateDate(Timestamp.valueOf("2020-11-03 16:27:26.0"));
        giftCertificateList.add(giftCertificate);
        giftCertificateList.add(giftCertificate);
        String searchQuery = "";
        Mockito.when(giftCertificateDao.findGiftCertificatesByParameters(searchQuery)).thenReturn(giftCertificateList);
        List<GiftCertificateDto> resultGiftCertificateDtoList = giftCertificateService.findGiftCertificates
                (Mockito.anyMap());
        Assertions.assertEquals(2, resultGiftCertificateDtoList.size());
    }

    @Test
    void addGiftCertificateTest() {
        GiftCertificate giftCertificate = new GiftCertificate();
        giftCertificate.setId(1);
        giftCertificate.setLastUpdateDate(Timestamp.valueOf("2020-11-03 16:27:26.0"));
        giftCertificate.setCreateDate(Timestamp.valueOf("2020-11-03 16:27:26.0"));
        GiftCertificateDto dtoToAdd = new GiftCertificateDto();
        GiftCertificateDto returnedGiftCertificateDto = new GiftCertificateDto();
        returnedGiftCertificateDto.setId(1);
        returnedGiftCertificateDto.setLastUpdateDate("2020-11-03T13:27Z");
        returnedGiftCertificateDto.setCreateDate("2020-11-03T13:27Z");
        Mockito.when(giftCertificateDao.create(Mockito.any())).thenReturn(giftCertificate);
        GiftCertificateDto resultGiftCertificateDto = giftCertificateService.addGiftCertificate(dtoToAdd);
        Assertions.assertEquals(returnedGiftCertificateDto, resultGiftCertificateDto);
    }

    @Test
    void updateGiftCertificateTest() {
        GiftCertificateDto giftCertificateDtoForUpdate = new GiftCertificateDto();
        giftCertificateDtoForUpdate.setId(1);
        giftCertificateDtoForUpdate.setName("Name");
        GiftCertificateDto returnedGiftCertificateDto = new GiftCertificateDto();
        returnedGiftCertificateDto.setName("Name");
        GiftCertificate updatedGiftCertificate = new GiftCertificate();
        updatedGiftCertificate.setName("Name");
        updatedGiftCertificate.setId(1);
        updatedGiftCertificate.setCreateDate(Timestamp.valueOf("2020-11-03 16:27:26.0"));
        Mockito.when(giftCertificateDao.update(Mockito.any())).thenReturn(updatedGiftCertificate);
        Mockito.when(giftCertificateDao.findById(1)).thenReturn(updatedGiftCertificate);
        GiftCertificateDto resultGifCertificateDto = giftCertificateService.updateGiftCertificate
                (giftCertificateDtoForUpdate, giftCertificateDtoForUpdate.getId());
        Assertions.assertEquals(giftCertificateDtoForUpdate.getName(), resultGifCertificateDto.getName());
        Assertions.assertNotNull(resultGifCertificateDto.getLastUpdateDate());
    }

    @Test
    void addGiftGiftCertificateTest_Should_Throw_Exception() {
        GiftCertificateDto giftCertificateDtoToAdd = new GiftCertificateDto();
        giftCertificateDtoToAdd.setName("Name");
        GiftCertificate existingGiftCertificate = new GiftCertificate();
        Mockito.when(giftCertificateDao.findByName("Name")).thenReturn(Optional.of(existingGiftCertificate));
        Assertions.assertThrows(ResourceAlreadyExistsException.class, () -> giftCertificateService.
                addGiftCertificate(giftCertificateDtoToAdd));
    }

    @Test
    void findGiftCertificateByIdTest_Should_Throw_Exception() {
        Mockito.when(giftCertificateDao.findById(1)).thenThrow(ResourceNotFoundException.class);
        Assertions.assertThrows(ResourceNotFoundException.class, () -> giftCertificateService.findGiftCertificateById(1));
    }
}
