package com.epam.esm.controller;

import com.epam.esm.entity.Tag;
import com.epam.esm.service.TagService;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/tags")
public class TagController {
    private final TagService tagService;

    public TagController(TagService tagService) {
        this.tagService = tagService;
    }

    @GetMapping(value = "/{id}")
    public Tag findTagById(@PathVariable Integer id) {
        return tagService.findTagById(id);
    }

    @PostMapping
    public Tag createTag(@Valid @RequestBody Tag tag) {
        return tagService.createTag(tag);
    }

    @DeleteMapping(value = "/{id}")
    public void deleteTag(@PathVariable Integer id) {
        tagService.deleteTag(id);
    }

    @GetMapping
    public List<Tag> findAllTags() {
        return tagService.findAllTags();
    }
}