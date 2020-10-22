package com.epam.esm.repository;

public final class SqlQuery {

    public static final String ADD_TAG = "INSERT INTO tag (name) VALUES (?)";
    public static final String FIND_TAG_BY_ID = "SELECT tag_id, name FROM tag WHERE tag_id = ?";

    private SqlQuery(){}

}
