package com.epam.esm.dao.tag;

import com.epam.esm.entity.Tag;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class TagMapper implements RowMapper<Tag> {

    @Override
    public Tag mapRow(ResultSet rs, int rowNum) throws SQLException {
        Tag tag = new Tag();
        tag.setId(rs.getInt(TagSqlColumnName.TAG_ID_COLUMN_NAME));
        tag.setName(rs.getString(TagSqlColumnName.TAG_NAME_COLUMN_NAME));
        return tag;
    }
}
