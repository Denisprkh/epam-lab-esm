package com.epam.esm.repository.tag;

import com.epam.esm.builder.TagBuilder;
import com.epam.esm.entity.Tag;
import com.epam.esm.repository.SqlColumnName;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class TagMapper implements RowMapper<Tag> {

    @Override
    public Tag mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new TagBuilder()
                .buildWithId(rs.getInt(SqlColumnName.TAG_ID_COLUMN_NAME))
                .buildWithName(rs.getString(SqlColumnName.TAG_NAME_COLUMN_NAME))
                .buildTag();
    }
}
