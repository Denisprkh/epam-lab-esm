package com.epam.esm.dao.giftcertificate;

public final class GiftCertificateSqlQuery {

    public static final String SELECT_ALL_FIELDS = "SELECT gift_certificate.gift_certificate_id,gift_certificate.name," +
            "gift_certificate.price,gift_certificate.create_date,gift_certificate.last_update_date," +
            "gift_certificate.duration,gift_certificate.description FROM gift_certificate";
    public static final String ADD_GIFT_CERTIFICATE = "INSERT INTO gift_certificate (name,price,create_date," +
            "last_update_date,duration,description) VALUES (?,?,?,?,?,?)";
    public static final String FIND_GIFT_CERTIFICATE_BY_ID = SELECT_ALL_FIELDS + " WHERE gift_certificate_id = ? ";
    public static final String DELETE_GIFT_CERTIFICATE_BY_ID = "DELETE FROM gift_certificate WHERE gift_certificate_id" +
            " = ?";
    public static final String FIND_ALL_GIFT_CERTIFICATES = SELECT_ALL_FIELDS;
    public static final String UPDATE_CERTIFICATE_BY_ID = "UPDATE gift_certificate SET name = ?, price = ?, last_update_date = ?," +
            "duration = ?, description = ? WHERE gift_certificate_id = ?";
    public static final String ADD_GIFT_CERTIFICATES_TAG = "INSERT INTO gift_certificates_tag (tag_id, " +
            "gift_certificate_id) VALUES (?,?)";
    public static final String DELETE_GIFT_CERTIFICATES_TAGS = "DELETE FROM gift_certificates_tag WHERE " +
            "gift_certificate_id = ?";
    public static final String FIND_GIFT_CERTIFICATE_BY_NAME = SELECT_ALL_FIELDS + " WHERE gift_certificate.name = ?";

    private GiftCertificateSqlQuery() {
    }
}
