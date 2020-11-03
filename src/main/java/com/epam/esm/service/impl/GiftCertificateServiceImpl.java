package com.epam.esm.service.impl;

import com.epam.esm.dto.GiftCertificateDto;
import com.epam.esm.dto.mapper.impl.GiftCertificateDtoMapper;
import com.epam.esm.entity.GiftCertificate;
import com.epam.esm.dao.giftcertificate.GiftCertificateDao;
import com.epam.esm.entity.Tag;
import com.epam.esm.exception.ResourceAlreadyExistsException;
import com.epam.esm.service.GiftCertificateService;
import com.epam.esm.service.TagService;
import com.epam.esm.service.util.GiftCertificateSearchQueryBuilder;
import com.epam.esm.util.ResourceBundleErrorMessage;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import static java.util.Objects.nonNull;


@Component
public class GiftCertificateServiceImpl implements GiftCertificateService {

    private final GiftCertificateDao giftCertificateDao;
    private final TagService tagService;
    private final GiftCertificateDtoMapper mapper;

    public GiftCertificateServiceImpl(GiftCertificateDao giftCertificateDao, TagService tagService,
                                      GiftCertificateDtoMapper mapper) {
        this.giftCertificateDao = giftCertificateDao;
        this.tagService = tagService;
        this.mapper = mapper;
    }

    @Override
    @Transactional
    public GiftCertificateDto addGiftCertificate(GiftCertificateDto giftCertificateDto) {
        Optional<GiftCertificate> foundByNameCertificate = giftCertificateDao.findByName(giftCertificateDto.getName());
        if (foundByNameCertificate.isPresent()) {
            throw new ResourceAlreadyExistsException(ResourceBundleErrorMessage.CERTIFICATE_ALREADY_EXISTS_ERROR_MESSAGE,
                    foundByNameCertificate.get().getId());
        }
        GiftCertificate createdGiftCertificate = createGiftCertificate(giftCertificateDto);
        if (nonNull(giftCertificateDto.getTags())) {
            List<Tag> identifiedTags = identifyGiftCertificatesTags(giftCertificateDto);
            createdGiftCertificate.setTags(identifiedTags);
            addGiftCertificatesTags(identifiedTags, createdGiftCertificate);
        }
        return mapper.toDto(createdGiftCertificate);
    }

    @Override
    public GiftCertificateDto findGiftCertificateById(Integer id) {
        GiftCertificate giftCertificate = giftCertificateDao.findById(id);
        giftCertificate.setTags(tagService.findGiftCertificatesTags(id));
        return mapper.toDto(giftCertificate);
    }

    @Override
    @Transactional
    public boolean deleteGiftCertificate(Integer id) {
        boolean giftCertificatesTagsAreDeleted = giftCertificateDao.deleteGiftCertificatesTags(id);
        boolean giftCertificateIsDeleted = giftCertificateDao.delete(id);
        return giftCertificatesTagsAreDeleted && giftCertificateIsDeleted;
    }

    @Override
    @Transactional
    public GiftCertificateDto updateGiftCertificate(GiftCertificateDto giftCertificatedto, Integer id) {
        GiftCertificate existingCertificate = giftCertificateDao.findById(id);
        GiftCertificate updatedGiftCertificate = mapper.toModel(giftCertificatedto);
        existingCertificate.merge(updatedGiftCertificate);
        existingCertificate.setLastUpdateDate(Timestamp.valueOf(LocalDateTime.now()));
        if (nonNull(giftCertificatedto.getTags())) {
            List<Tag> identifiedTags = identifyGiftCertificatesTags(giftCertificatedto);
            List<Tag> existingTags = tagService.findGiftCertificatesTags(id);
            identifiedTags.removeAll(existingTags);
            addGiftCertificatesTags(identifiedTags, existingCertificate);
        }
        existingCertificate.setTags(tagService.findGiftCertificatesTags(id));
        return mapper.toDto(giftCertificateDao.update(existingCertificate));
    }

    @Override
    public List<GiftCertificateDto> findGiftCertificates(Map<String, String> params) {
        List<GiftCertificate> foundGiftCertificates = giftCertificateDao.findGiftCertificatesByParameters
                (new GiftCertificateSearchQueryBuilder().buildQuery(params));
        setGiftCertificatesTags(foundGiftCertificates);
        return mapCertificatesToDto(foundGiftCertificates);
    }

    private List<GiftCertificateDto> mapCertificatesToDto(List<GiftCertificate> giftCertificates) {
        return giftCertificates.stream().map(mapper::toDto).collect(Collectors.toList());
    }

    private void setGiftCertificatesTags(List<GiftCertificate> giftCertificates) {
        giftCertificates.forEach(giftCertificate -> giftCertificate.setTags(tagService.findGiftCertificatesTags
                (giftCertificate.getId())));
    }

    private GiftCertificate createGiftCertificate(GiftCertificateDto giftCertificateDto) {
        GiftCertificate giftCertificate = mapper.toModel(giftCertificateDto);
        setCreateAndUpdateDate(giftCertificate);
        return giftCertificateDao.create(giftCertificate);
    }

    private void addGiftCertificatesTags(List<Tag> giftCertificatesTags, GiftCertificate giftCertificate) {
        giftCertificateDao.addGiftCertificatesTags(giftCertificatesTags, giftCertificate);
    }

    private List<Tag> identifyGiftCertificatesTags(GiftCertificateDto giftCertificateDto) {
        List<Tag> identifiedGiftCertificateTags = giftCertificateDto.getTags().stream().map(tagName -> {
            if (tagService.findTagByName(tagName).isPresent()) {
                return tagService.findTagByName(tagName).get();
            } else {
                return tagService.createTag(new Tag(tagName));
            }
        }).collect(Collectors.toList());
        return identifiedGiftCertificateTags;
    }

    private GiftCertificate setCreateAndUpdateDate(GiftCertificate giftCertificate) {
        giftCertificate.setCreateDate(Timestamp.valueOf(LocalDateTime.now()));
        giftCertificate.setLastUpdateDate(Timestamp.valueOf(LocalDateTime.now()));
        return giftCertificate;
    }

}
