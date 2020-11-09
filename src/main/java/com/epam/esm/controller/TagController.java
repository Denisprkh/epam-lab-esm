package com.epam.esm.controller;

import com.epam.esm.entity.Tag;
import com.epam.esm.service.TagService;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * Controller used to manipulate CRD operations on
 * {@code Tag} data
 */
@RestController
@RequestMapping("/tags")
public class TagController {
    private final TagService tagService;

    public TagController(TagService tagService) {
        this.tagService = tagService;
    }


    /**
     * Finds tag requested by id.
     * If no resource found {@code HttpStatus.NOT_FOUND} is returned.
     *
     * @param id {@code Tag}'s id.
     * @return {@code Tag}.
     */
    @GetMapping(value = "/{id}")
    public Tag findTagById(@PathVariable Integer id) {
        return tagService.findTagById(id);
    }

    /**
     * Creates a new Tag in the database.
     * If resource is already exists {@code HttpStatus.BAD_REQUEST} is returned.
     *
     * @param tag {@code Tag} to create.
     * @return {@code Tag} that was created.
     */
    @PostMapping
    public Tag createTag(@Valid @RequestBody Tag tag) {
        return tagService.createTag(tag);
    }

    /**
     * Deletes tag with the specified id.
     *
     * @param id {@code Tag}'s id.
     */
    @DeleteMapping(value = "/{id}")
    public void deleteTag(@PathVariable Integer id) {
        tagService.deleteTag(id);
    }

    /**
     * Finds all tags.
     * If no resource found {@code HttpStatus.NOT_FOUND} is returned.
     *
     * @return List of {@code Tag}.
     */
    @GetMapping
    public List<Tag> findAllTags() {
        return tagService.findAllTags();
    }
}