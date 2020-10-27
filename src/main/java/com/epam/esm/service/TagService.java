package com.epam.esm.service;

import com.epam.esm.entity.Tag;

import java.util.List;

public interface TagService {

    Tag createTag(Tag tag);

    Tag findTagById(Integer id);

    boolean deleteTag(Integer id);

    List<Tag> findAllTags();

    boolean isTagExists(String tagName);

    List<Tag> createAllTags(List<Tag> tags);

    Tag findTagByName(String tagName);

    List<Tag> findGiftCertificatesTags(Integer giftCertificatesId);

}
