package com.epam.esm.dao.tag;

import com.epam.esm.entity.Tag;
import com.epam.esm.dao.CommonDao;

import java.util.List;
import java.util.Optional;

public interface TagDao extends CommonDao<Tag, Integer> {

    Optional<Tag> findTagByName(String tagName);

    List<Tag> findAllGiftCertificatesTagsById(Integer giftCertificateId);

    boolean deleteGiftCertificateTag(Integer tagId);
}
