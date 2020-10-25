package com.epam.esm.service;

import com.epam.esm.entity.Tag;

import java.util.List;

public interface TagService {

    Tag createTag(Tag tag);

    Tag findTagById(Integer id);

    boolean deleteTag(Tag tag);

    List<Tag> findAllTags();

}
