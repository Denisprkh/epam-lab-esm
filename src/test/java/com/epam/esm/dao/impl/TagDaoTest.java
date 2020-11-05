package com.epam.esm.dao.impl;

import com.epam.esm.config.TestConfig;
import com.epam.esm.dao.tag.TagDao;
import com.epam.esm.entity.Tag;
import com.epam.esm.exception.ResourceNotFoundException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;


import java.util.List;

@Component
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {TestConfig.class})
public class TagDaoTest {

    @Qualifier("getTagDaoTest")
    @Autowired
    private TagDao tagDao;

    @Test
    @Order(1)
    void findByIdTest() {
        int expectedTagId = 1;
        int resultTagId = tagDao.findById(1).getId();
        Assertions.assertEquals(expectedTagId, resultTagId);
    }

    @Test
    @Order(2)
    void createTest() {
        Tag tagForCreation = new Tag("Name");
        String expectedTagName = "Name";
        String resultTagName = tagDao.create(tagForCreation).getName();
        Assertions.assertEquals(expectedTagName, resultTagName);
    }

    @Test
    @Order(3)
    void findAllTest_Should_Return_List_Of_Three_Tags() {
        List<Tag> allTags = tagDao.findAll();
        Assertions.assertEquals(3, allTags.size());
    }

    @Test
    @Order(4)
    void deleteTagTest() {
        tagDao.delete(tagDao.create(new Tag("deleteTest")).getId());
        int expectedAllTagsSize = 3;
        List<Tag> allTags = tagDao.findAll();
        Assertions.assertEquals(expectedAllTagsSize, allTags.size());
    }

    @Test
    @Order(5)
    void findByIdTest_Should_Throw_Exception() {
        Assertions.assertThrows(ResourceNotFoundException.class, () -> tagDao.findById(50));
    }

    @Test
    @Order(6)
    void findByNameTest() {
        Tag expectedTag = new Tag(1, "sport");
        Tag resultTag = tagDao.findTagByName("sport").get();
        Assertions.assertEquals(expectedTag, resultTag);
    }

    @Test
    @Order(7)
    void findAllGiftCertificatesTagsByIdTest() {
        int expectedTagsSize = 2;
        int resultSize = tagDao.findAllGiftCertificatesTagsById(2).size();
        Assertions.assertEquals(expectedTagsSize, resultSize);
    }

}
