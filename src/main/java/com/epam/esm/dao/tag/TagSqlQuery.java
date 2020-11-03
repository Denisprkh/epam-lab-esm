package com.epam.esm.dao.tag;

public final class TagSqlQuery {

    public static final String ADD_TAG = "INSERT INTO tag (name) VALUES (?)";
    public static final String FIND_TAG_BY_ID = "SELECT tag_id, name FROM tag WHERE tag_id = ?";
    public static final String DELETE_TAG_BY_ID = "DELETE FROM tag WHERE tag_id = ?";
    public static final String FIND_ALL_TAGS = "SELECT tag_id,name FROM tag";
    public static final String FIND_TAG_BY_NAME = "SELECT tag_id, name FROM tag WHERE name = ?";
    public static final String FIND_TAGS_BY_GIFT_CERTIFICATES_ID = "SELECT tag.tag_id, tag.name FROM tag JOIN " +
            "gift_certificates_tag ON tag.tag_id = gift_certificates_tag.tag_id JOIN gift_certificate ON " +
            "gift_certificates_tag.gift_certificate_id = gift_certificate.gift_certificate_id WHERE " +
            "gift_certificate.gift_certificate_id = ?";

    private TagSqlQuery() {
    }

}
