package com.epam.esm.repository.tag.impl;

import com.epam.esm.entity.Tag;
import com.epam.esm.repository.SqlQuery;
import com.epam.esm.repository.tag.TagMapper;
import com.epam.esm.repository.tag.TagRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.Objects;

@Component
public class TagRepositoryImpl implements TagRepository{

    private DataSource dataSource;
    private JdbcTemplate jdbcTemplate;

    @Autowired
    @Qualifier("dataSource")
    public void setDataSource(DataSource dataSource){
        this.dataSource = dataSource;
        this.jdbcTemplate = new JdbcTemplate(this.dataSource);
    }

    @Override
    public Tag create(Tag tag) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(connection ->{
            PreparedStatement statement = connection.prepareStatement(SqlQuery.ADD_TAG,
                    Statement.RETURN_GENERATED_KEYS);
            statement.setString(1,tag.getName());
            return statement;
        },keyHolder);
        tag.setId(Objects.requireNonNull(keyHolder.getKey()).intValue());
        return tag;
    }

    @Override
    public Tag findById(Integer id) {
        return jdbcTemplate.queryForObject(SqlQuery.FIND_TAG_BY_ID,new TagMapper(), id);
    }

    @Override
    public void delete(Tag tag) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void update(Tag tag) {
        throw new UnsupportedOperationException();
    }
}
