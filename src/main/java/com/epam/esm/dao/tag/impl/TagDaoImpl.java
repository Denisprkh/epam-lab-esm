package com.epam.esm.dao.tag.impl;

import com.epam.esm.entity.Tag;
import com.epam.esm.dao.tag.TagMapper;
import com.epam.esm.dao.tag.TagDao;
import com.epam.esm.dao.tag.TagSqlQuery;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Component
public class TagDaoImpl implements TagDao {

    private final DataSource dataSource;
    private final JdbcTemplate jdbcTemplate;
    private final TagMapper tagMapper;
    private static final int SUCCESSFULLY_UPDATED_ROW = 1;

    public TagDaoImpl(DataSource dataSource, TagMapper tagMapper) {
        this.dataSource = dataSource;
        this.jdbcTemplate = new JdbcTemplate(this.dataSource);
        this.tagMapper = tagMapper;
    }

    @Override
    public Tag create(Tag tag) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(connection -> {
            PreparedStatement statement = connection.prepareStatement(TagSqlQuery.ADD_TAG,
                    Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, tag.getName());
            return statement;
        }, keyHolder);
        tag.setId(Objects.requireNonNull(keyHolder.getKey()).intValue());
        return tag;
    }

    @Override
    public Tag findById(Integer id) {
        return jdbcTemplate.queryForObject(TagSqlQuery.FIND_TAG_BY_ID, tagMapper, id);
    }

    @Override
    public boolean delete(Integer id) {
        return jdbcTemplate.update(TagSqlQuery.DELETE_TAG_BY_ID, id) == SUCCESSFULLY_UPDATED_ROW;
    }

    @Override
    public Tag update(Tag tag) {
        throw new UnsupportedOperationException();
    }

    @Override
    public List<Tag> findAll() {
        return jdbcTemplate.query(TagSqlQuery.FIND_ALL_TAGS, tagMapper);
    }

    @Override
    public Optional<Tag> findTagByName(String tagName) {
        return Optional.ofNullable(jdbcTemplate.queryForObject(TagSqlQuery.FIND_TAG_BY_NAME, tagMapper, tagName));
    }

    @Override
    public List<Tag> findAllGiftCertificatesTagsById(Integer giftCertificateId) {
        return jdbcTemplate.query(TagSqlQuery.FIND_TAGS_BY_GIFT_CERTIFICATES_ID, tagMapper, giftCertificateId);
    }

}