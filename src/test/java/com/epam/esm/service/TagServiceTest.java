package com.epam.esm.service;

import com.epam.esm.dao.tag.TagDao;
import com.epam.esm.entity.Tag;
import com.epam.esm.exception.ResourceAlreadyExistsException;
import com.epam.esm.exception.ResourceNotFoundException;
import com.epam.esm.service.impl.TagServiceImpl;
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
    void findTagByIdTest(){
        Tag tag = new Tag(1,"name");
        Mockito.when(tagDao.findById(1)).thenReturn(tag);
        Assertions.assertEquals(tag,tagService.findTagById(1));
    }

    @Test
    void findTagByNameTest(){
        Tag tag = new Tag(1, "name");
        Mockito.when(tagDao.findTagByName("name")).thenReturn(Optional.of(tag));
        Assertions.assertEquals(Optional.of(tag),tagService.findTagByName("name"));
    }

    @Test
    void findAllTagsTest(){
        Tag tag = new Tag("name");
        List<Tag> tags = Collections.singletonList(tag);
        List<Tag> allTags = Collections.singletonList(tag);
        Mockito.when(tagDao.findAll()).thenReturn(allTags);
        Assertions.assertEquals(tags,allTags);
    }

    @Test
    void deleteTagTest(){
        Mockito.when(tagDao.delete(1)).thenReturn(Boolean.TRUE);
        Mockito.when(tagDao.deleteGiftCertificateTag(1)).thenReturn(Boolean.TRUE);
        Assertions.assertEquals(Boolean.TRUE, tagService.deleteTag(1));
        Mockito.verify(tagDao,Mockito.times(1)).delete(1);
        Mockito.verify(tagDao,Mockito.times(1)).deleteGiftCertificateTag(1);
    }

    @Test
    void createTagTest(){
        Tag tagForCreating = new Tag("name");
        Tag createdTag = new Tag(1,"name");
        Mockito.when(tagDao.create(tagForCreating)).thenReturn(createdTag);
        Assertions.assertEquals(createdTag,tagService.createTag(tagForCreating));
    }

    @Test
    void createTagTest_NotUniqueName(){
        Tag tagForCreating = new Tag("name");
        Tag existingTag = new Tag(1,"name");
        Mockito.when(tagDao.findTagByName("name")).thenReturn(Optional.of(existingTag));
        Assertions.assertThrows(ResourceAlreadyExistsException.class, () -> tagService.createTag(tagForCreating));
    }

    @Test
    void findTagByIdTest_NotExistsTag(){
        Mockito.when(tagDao.findById(3)).thenThrow(ResourceNotFoundException.class);
        Assertions.assertThrows(ResourceNotFoundException.class, () -> tagService.findTagById(3));
    }
}
