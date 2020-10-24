package com.epam.esm.service;

import com.epam.esm.entity.Tag;

public interface TagService {

    Tag createTag(Tag tag);

    Tag findTagById(Integer id);

    void deleteTag(Tag tag);

}
