package com.epam.esm.service;

import com.epam.esm.entity.Tag;

import java.util.List;
import java.util.Optional;

public interface TagService {

    Tag createTag(Tag tag);

    Tag findTagById(Integer id);

    boolean deleteTag(Integer id);

    List<Tag> findAllTags();

    Optional<Tag> findTagByName(String tagName);

    List<Tag> findGiftCertificatesTags(Integer giftCertificatesId);

}
