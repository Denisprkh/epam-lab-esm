package com.epam.esm.service.impl;

import com.epam.esm.entity.Tag;
import com.epam.esm.repository.tag.TagRepository;
import com.epam.esm.service.TagService;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class TagServiceImpl implements TagService {

    private final TagRepository tagRepository;

    public TagServiceImpl(TagRepository tagRepository) {
        this.tagRepository = tagRepository;
    }

    @Override
    public Tag createTag(Tag tag) {
        return tagRepository.create(tag);
    }

    @Override
    public Tag findTagById(Integer id) {
        return tagRepository.findById(id);
    }

    @Override
    public boolean deleteTag(Tag tag) {
        return tagRepository.delete(tag);
    }

    @Override
    public List<Tag> findAllTags() {
        return tagRepository.findAll();
    }
}
