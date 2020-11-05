package com.epam.esm.service.impl;

import com.epam.esm.dao.tag.TagDao;
import com.epam.esm.entity.Tag;
import com.epam.esm.exception.ResourceAlreadyExistsException;
import com.epam.esm.exception.ResourceNotFoundException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class TagServiceTest {

    @Mock
    private TagDao tagDao;

    @InjectMocks
    private TagServiceImpl tagService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void findTagByIdTest() {
        Tag tag = new Tag(1, "name");
        Mockito.when(tagDao.findById(1)).thenReturn(tag);
        Tag resultTag = tagService.findTagById(1);
        Assertions.assertEquals(tag, resultTag);
    }

    @Test
    void findTagByNameTest() {
        Tag tag = new Tag(1, "name");
        Mockito.when(tagDao.findTagByName("name")).thenReturn(Optional.of(tag));
        Tag resultTag = tagService.findTagByName("name").get();
        Assertions.assertEquals(tag, resultTag);
    }

    @Test
    void findAllTagsTest() {
        Tag tag = new Tag("name");
        List<Tag> allTags = Collections.singletonList(tag);
        List<Tag> expectedTags = Collections.singletonList(tag);
        Mockito.when(tagDao.findAll()).thenReturn(allTags);
        List<Tag> resultTagList = tagService.findAllTags();
        Assertions.assertEquals(expectedTags, resultTagList);
    }

    @Test
    void deleteTagTest() {
        Mockito.when(tagDao.delete(1)).thenReturn(Boolean.TRUE);
        Mockito.when(tagDao.deleteGiftCertificateTag(1)).thenReturn(Boolean.TRUE);
        Assertions.assertEquals(Boolean.TRUE, tagService.deleteTag(1));
        Mockito.verify(tagDao, Mockito.times(1)).delete(1);
        Mockito.verify(tagDao, Mockito.times(1)).deleteGiftCertificateTag(1);
    }

    @Test
    void createTagTest() {
        Tag tagForCreating = new Tag("name");
        Tag createdTag = new Tag(1, "name");
        Mockito.when(tagDao.create(tagForCreating)).thenReturn(createdTag);
        Tag resultTag = tagService.createTag(tagForCreating);
        Assertions.assertEquals(createdTag, resultTag);
    }

    @Test
    void createTagTest_Should_Throw_Exception() {
        Tag tagForCreating = new Tag("name");
        Tag existingTag = new Tag(1, "name");
        Mockito.when(tagDao.findTagByName("name")).thenReturn(Optional.of(existingTag));
        Assertions.assertThrows(ResourceAlreadyExistsException.class, () -> tagService.createTag(tagForCreating));
    }

    @Test
    void findTagByIdTest_Should_Throw_Exception() {
        Mockito.when(tagDao.findById(3)).thenThrow(ResourceNotFoundException.class);
        Assertions.assertThrows(ResourceNotFoundException.class, () -> tagService.findTagById(3));
    }
}
