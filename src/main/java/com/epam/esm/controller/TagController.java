package com.epam.esm.controller;

import com.epam.esm.entity.Tag;
import com.epam.esm.service.TagService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tags")
public class TagController {
    private final TagService tagService;

    public TagController(TagService tagService) {
        this.tagService = tagService;
    }

    @GetMapping(value = "/{id}")
    public Tag findTagById(@PathVariable int id) {
        return tagService.findTagById(id);
    }

    @PostMapping
    public Tag createTag(@RequestBody Tag tag) {
        return tagService.createTag(tag);
    }

    @DeleteMapping
    public void deleteTag(@RequestBody Tag tag) {
        tagService.deleteTag(tag);
    }

    @GetMapping
    public List<Tag> findAllTags() {
        return tagService.findAllTags();
    }
}