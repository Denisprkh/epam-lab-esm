package com.epam.esm.dao.tag;

import com.epam.esm.entity.Tag;
import com.epam.esm.dao.CommonDao;

import java.util.List;

public interface TagDao extends CommonDao<Tag, Integer> {
    List<Tag> createAll(List<Tag> tags);

    boolean isTagExists(String tagName);

    Tag findTagByName(String tagName);

    List<Tag> findAllGiftCertificatesTagsById(Integer giftCertificateId);
}
