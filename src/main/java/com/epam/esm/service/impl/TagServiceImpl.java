package com.epam.esm.service.impl;

import com.epam.esm.entity.Tag;
import com.epam.esm.repository.tag.TagRepository;
import com.epam.esm.repository.tag.impl.TagRepositoryImpl;
import com.epam.esm.service.TagService;
import org.springframework.stereotype.Component;

@Component
public class TagServiceImpl implements TagService {

    private TagRepository tagRepository;

    public TagServiceImpl(TagRepositoryImpl tagRepository){
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
}
