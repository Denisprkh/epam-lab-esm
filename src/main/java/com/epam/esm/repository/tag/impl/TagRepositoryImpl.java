package com.epam.esm.repository.tag.impl;

import com.epam.esm.entity.Tag;
import com.epam.esm.repository.tag.TagMapper;
import com.epam.esm.repository.tag.TagRepository;
import com.epam.esm.repository.tag.TagSqlQuery;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;
import java.util.Objects;

@Component
public class TagRepositoryImpl implements TagRepository {

    private final DataSource dataSource;
    private final JdbcTemplate jdbcTemplate;
    private static final int SUCCESSFULLY_UPDATED_ROW = 1;

    public TagRepositoryImpl(DataSource dataSource) {
        this.dataSource = dataSource;
        this.jdbcTemplate = new JdbcTemplate(this.dataSource);
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
        return jdbcTemplate.queryForObject(TagSqlQuery.FIND_TAG_BY_ID, new TagMapper(), id);
    }

    @Override
    public boolean delete(Tag tag) {
        return jdbcTemplate.update(TagSqlQuery.DELETE_TAG_BY_ID, tag.getId()) == SUCCESSFULLY_UPDATED_ROW;
    }

    @Override
    public void update(Tag tag) {
        throw new UnsupportedOperationException();
    }

    @Override
    public List<Tag> findAll() {
        return jdbcTemplate.query(TagSqlQuery.FIND_ALL_TAGS, new TagMapper());
    }

}
