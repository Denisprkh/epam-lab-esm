package com.epam.esm.repository.tag;

public final class TagSqlQuery {

    public static final String ADD_TAG = "INSERT INTO tag (name) VALUES (?)";
    public static final String FIND_TAG_BY_ID = "SELECT tag_id, name FROM tag WHERE tag_id = ?";
    public static final String DELETE_TAG_BY_ID = "DELETE FROM tag WHERE tag_id = ?";
    public static final String FIND_ALL_TAGS = "SELECT tag_id,name FROM tag";

    private TagSqlQuery() {
    }

}
