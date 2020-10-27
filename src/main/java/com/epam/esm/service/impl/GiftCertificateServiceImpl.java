package com.epam.esm.service.impl;

import com.epam.esm.entity.GiftCertificate;
import com.epam.esm.dao.giftcertificate.GiftCertificateDao;
import com.epam.esm.entity.Tag;
import com.epam.esm.service.GiftCertificateService;
import com.epam.esm.service.TagService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;


@Component
public class GiftCertificateServiceImpl implements GiftCertificateService {

    private final GiftCertificateDao giftCertificateDao;
    private final TagService tagService;
    private static final Logger LOG = LogManager.getLogger();

    public GiftCertificateServiceImpl(GiftCertificateDao giftCertificateDao, TagService tagService) {
        this.giftCertificateDao = giftCertificateDao;
        this.tagService = tagService;
    }

    @Override
    @Transactional
    public GiftCertificate addGiftCertificateAndItsTags(GiftCertificate giftCertificate) {
        GiftCertificate createdGiftCertificate = addGiftCertificate(giftCertificate);
        return addGiftCertificatesTags(createdGiftCertificate);
    }

    @Override
    public GiftCertificate findGiftCertificateById(Integer id) {
        return giftCertificateDao.findById(id);
    }

    @Override
    public void deleteGiftCertificate(Integer id) {
        giftCertificateDao.delete(id);
    }

    @Override
    @Transactional
    public GiftCertificate updateGiftCertificate(GiftCertificate giftCertificate) {
        GiftCertificate giftCertificateForUpdate = findGiftCertificateById(giftCertificate.getId());
        giftCertificateForUpdate.merge(giftCertificate);
        List<Tag> newAddedTags = createNewGiftCertificatesTags(giftCertificateForUpdate);
        giftCertificateDao.addGiftCertificatesTags(newAddedTags, giftCertificateForUpdate);
        giftCertificateForUpdate.setGiftCertificatesTags(tagService.findGiftCertificatesTags(giftCertificate.getId()));
        return giftCertificateDao.update(giftCertificateForUpdate);
    }

    @Override
    public List<GiftCertificate> findAllGiftCertificates() {
        return giftCertificateDao.findAll();
    }

    private GiftCertificate addGiftCertificatesTags(GiftCertificate giftCertificate) {
        List<Tag> existingTags = giftCertificate.getGiftCertificatesTags().stream().filter(tag ->
                tagService.isTagExists(tag.getName())).map(tag -> (tagService.findTagByName(tag.getName()))).
                collect(Collectors.toList());
        List<Tag> newAddedTags = createNewGiftCertificatesTags(giftCertificate);
        existingTags.addAll(newAddedTags);
        LOG.debug(existingTags);
        giftCertificate.setGiftCertificatesTags(existingTags);
        giftCertificateDao.addGiftCertificatesTags(existingTags, giftCertificate);
        return giftCertificate;
    }

    private List<Tag> createNewGiftCertificatesTags(GiftCertificate giftCertificate) {
        List<Tag> newTags = giftCertificate.getGiftCertificatesTags().stream().filter(tag ->
                !tagService.isTagExists(tag.getName())).collect(Collectors.toList());
        return tagService.createAllTags(newTags);
    }

    private GiftCertificate addGiftCertificate(GiftCertificate giftCertificate) {
        GiftCertificate giftCertificateWithDates = setCreateAndUpdateDate(giftCertificate);
        return giftCertificateDao.create(giftCertificateWithDates);
    }

    private GiftCertificate setCreateAndUpdateDate(GiftCertificate giftCertificate) {
        giftCertificate.setCreateDate(LocalDateTime.now());
        giftCertificate.setLastUpdateDate(LocalDateTime.now());
        return giftCertificate;
    }

}
