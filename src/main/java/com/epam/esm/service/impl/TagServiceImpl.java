package com.epam.esm.service.impl;

import com.epam.esm.entity.Tag;
import com.epam.esm.dao.tag.TagDao;
import com.epam.esm.exception.ResourceAlreadyExistsException;
import com.epam.esm.service.TagService;
import com.epam.esm.util.ResourceBundleErrorMessage;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class TagServiceImpl implements TagService {

    private final TagDao tagDao;

    public TagServiceImpl(TagDao tagDao) {
        this.tagDao = tagDao;
    }

    @Override
    public Tag createTag(Tag tag) {
        Optional<Tag> tagFoundByName = findTagByName(tag.getName());
        if (tagFoundByName.isPresent()) {
            throw new ResourceAlreadyExistsException(ResourceBundleErrorMessage.TAG_IS_ALREADY_EXISTS_ERROR_MESSAGE,
                    tagFoundByName.get().getId());
        }
        return tagDao.create(tag);
    }

    @Override
    public Tag findTagById(Integer id) {
        return tagDao.findById(id);
    }

    @Override
    public boolean deleteTag(Integer id) {
        return tagDao.delete(id);
    }

    @Override
    public List<Tag> findAllTags() {
        return tagDao.findAll();
    }

    @Override
    public Optional<Tag> findTagByName(String tagName) {
        return tagDao.findTagByName(tagName);
    }

    @Override
    public List<Tag> findGiftCertificatesTags(Integer giftCertificatesId) {
        return tagDao.findAllGiftCertificatesTagsById(giftCertificatesId);
    }

}
