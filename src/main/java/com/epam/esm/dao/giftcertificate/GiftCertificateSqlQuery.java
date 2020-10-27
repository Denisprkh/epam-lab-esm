package com.epam.esm.dao.giftcertificate;

public final class GiftCertificateSqlQuery {

    public static final String ADD_GIFT_CERTIFICATE = "INSERT INTO gift_certificate (name,price,create_date," +
            "last_update_date,duration,description) VALUES (?,?,?,?,?,?)";
    public static final String FIND_GIFT_CERTIFICATE_BY_ID = "SELECT gift_certificate_id,name,price,create_date," +
            "last_update_date,duration,description FROM gift_certificate WHERE gift_certificate_id = ? ";
    public static final String DELETE_GIFT_CERTIFICATE_BY_ID = "DELETE FROM gift_certificate WHERE gift_certificate_id" +
            " = ?";
    public static final String FIND_ALL_GIFT_CERTIFICATES = "SELECT gift_certificate_id,name,price,create_date," +
            "last_update_date,duration,description FROM gift_certificate";
    public static final String UPDATE_TAG_BY_ID = "UPDATE gift_certificate SET name = ?, price = ?, last_update_date = ?," +
            "duration = ?, description = ? WHERE gift_certificate_id = ?";
    public static final String ADD_GIFT_CERTIFICATES_TAG = "INSERT INTO gift_certificates_tag (tag_id, " +
            "gift_certificate_id) VALUES (?,?)";

    private GiftCertificateSqlQuery() {
    }
}
