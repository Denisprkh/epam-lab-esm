package com.epam.esm.service.impl;

import com.epam.esm.dao.giftcertificate.GiftCertificateDao;
import com.epam.esm.dto.GiftCertificateDto;
import com.epam.esm.dto.mapper.impl.GiftCertificateDtoMapper;
import com.epam.esm.entity.GiftCertificate;
import com.epam.esm.exception.ResourceAlreadyExistsException;
import com.epam.esm.exception.ResourceNotFoundException;
import com.epam.esm.service.TagService;
import com.epam.esm.service.util.GiftCertificateSearchQueryBuilder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertNotNull;

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

    @Mock
    private GiftCertificateDtoMapper dtoMapper;

    @Spy
    private GiftCertificateSearchQueryBuilder giftCertificateSearchQueryBuilder =
            new GiftCertificateSearchQueryBuilder();

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

        Mockito.when(dtoMapper.toDto(giftCertificate)).thenReturn(returnedDto);
        Mockito.when(giftCertificateDao.findById(1)).thenReturn(giftCertificate);
        GiftCertificateDto resultDto = giftCertificateService.findGiftCertificateById(1);

        assertEquals(returnedDto, resultDto);
        Mockito.verify(tagService, Mockito.times(1)).findGiftCertificatesTags(1);
    }

    @Test
    void deleteGiftCertificateTest() {
        Mockito.when(giftCertificateDao.delete(1)).thenReturn(Boolean.TRUE);
        Mockito.when(giftCertificateDao.findById(1)).thenReturn(new GiftCertificate());
        Mockito.when(giftCertificateDao.deleteGiftCertificatesTags(1)).thenReturn(Boolean.TRUE);

        assertEquals(Boolean.TRUE, giftCertificateService.deleteGiftCertificate(1));
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
        List<GiftCertificateDto> resultGiftCertificateDtoList = giftCertificateService.findGiftCertificates(Mockito.anyMap());

        assertEquals(2, resultGiftCertificateDtoList.size());
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

        Mockito.when(dtoMapper.toModel(dtoToAdd)).thenReturn(giftCertificate);
        Mockito.when(dtoMapper.toDto(giftCertificate)).thenReturn(returnedGiftCertificateDto);
        Mockito.when(giftCertificateDao.create(Mockito.any())).thenReturn(giftCertificate);
        GiftCertificateDto resultGiftCertificateDto = giftCertificateService.addGiftCertificate(dtoToAdd);

        assertEquals(returnedGiftCertificateDto, resultGiftCertificateDto);
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
        GiftCertificateDto updatedGifCertificateDto = new GiftCertificateDto();
        updatedGifCertificateDto.setId(1);
        updatedGifCertificateDto.setName("Name");
        updatedGifCertificateDto.setLastUpdateDate("2020-11-03 16:27:26.0");

        Mockito.when(giftCertificateDao.update(Mockito.any())).thenReturn(updatedGiftCertificate);
        Mockito.when(dtoMapper.toModel(giftCertificateDtoForUpdate)).thenReturn(updatedGiftCertificate);
        Mockito.when(dtoMapper.toDto(updatedGiftCertificate)).thenReturn(updatedGifCertificateDto);
        Mockito.when(giftCertificateDao.findById(1)).thenReturn(updatedGiftCertificate);
        GiftCertificateDto resultGifCertificateDto = giftCertificateService.updateGiftCertificate
                (giftCertificateDtoForUpdate, giftCertificateDtoForUpdate.getId());

        assertEquals(giftCertificateDtoForUpdate.getName(), resultGifCertificateDto.getName());
        assertNotNull(resultGifCertificateDto.getLastUpdateDate());
    }

    @Test
    void addGiftGiftCertificateTestShouldThrowException() {
        GiftCertificateDto giftCertificateDtoToAdd = new GiftCertificateDto();
        giftCertificateDtoToAdd.setName("Nam");
        GiftCertificate existingGiftCertificate = new GiftCertificate();

        Mockito.when(giftCertificateDao.findByName("Nam")).thenReturn(Optional.of(existingGiftCertificate));

        assertThrows(ResourceAlreadyExistsException.class, () -> giftCertificateService.
                addGiftCertificate(giftCertificateDtoToAdd));
    }

    @Test
    void findGiftCertificateByIdTestShouldThrowException() {
        Mockito.when(giftCertificateDao.findById(1)).thenThrow(ResourceNotFoundException.class);

        assertThrows(ResourceNotFoundException.class, () -> giftCertificateService.findGiftCertificateById(1));
    }
}
