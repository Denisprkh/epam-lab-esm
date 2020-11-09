package com.epam.esm.dao.impl;

import com.epam.esm.config.TestConfig;
import com.epam.esm.dao.tag.TagDao;
import com.epam.esm.entity.Tag;
import com.epam.esm.exception.ResourceNotFoundException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.List;

@Component
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {TestConfig.class})
public class TagDaoTest {

    @Qualifier("tagDao")
    @Autowired
    private TagDao tagDao;

    @Test
    void findByIdTest() {
        int expectedTagId = 1;

        Tag resultTag = tagDao.findById(1);

        assertEquals(expectedTagId, resultTag.getId());
    }

    @Test
    void createTest() {
        Tag tagForCreation = new Tag("Name");
        String expectedTagName = "Name";

        Tag resultTag = tagDao.create(tagForCreation);

        assertEquals(expectedTagName, resultTag.getName());
    }

    @Test
    void findAllTestShouldReturnListOfThreeTags() {
        int expectedListSize = 3;

        List<Tag> allTags = tagDao.findAll();

        assertEquals(expectedListSize, allTags.size());
    }

    @Test
    void deleteTagTest() {
        tagDao.delete(tagDao.create(new Tag("deleteTest")).getId());
        int expectedAllTagsSize = 3;

        List<Tag> allTags = tagDao.findAll();

        assertEquals(expectedAllTagsSize, allTags.size());
    }

    @Test
    void findByIdTestShouldThrowException() {
        assertThrows(ResourceNotFoundException.class, () -> tagDao.findById(50));
    }

    @Test
    void findByNameTest() {
        Tag expectedTag = new Tag(1, "sport");

        Tag resultTag = tagDao.findTagByName("sport").get();

        assertEquals(expectedTag, resultTag);
    }

    @Test
    void findAllGiftCertificatesTagsByIdTest() {
        int expectedTagsSize = 2;

        List<Tag> resultGiftCertificatesTags = tagDao.findAllGiftCertificatesTagsById(2);
        assertEquals(expectedTagsSize, resultGiftCertificatesTags.size());
    }

}
