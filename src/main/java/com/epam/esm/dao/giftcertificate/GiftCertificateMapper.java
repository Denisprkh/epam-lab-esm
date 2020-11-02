package com.epam.esm.dao.giftcertificate;

import com.epam.esm.entity.GiftCertificate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class GiftCertificateMapper implements RowMapper<GiftCertificate> {

    @Override
    public GiftCertificate mapRow(ResultSet rs, int rowNum) throws SQLException {
        GiftCertificate giftCertificate = new GiftCertificate();
        giftCertificate.setId(rs.getInt(GiftCertificateSqlColumnName.GIFT_CERTIFICATE_ID_COLUMN_NAME));
        giftCertificate.setName(rs.getString(GiftCertificateSqlColumnName.GIFT_CERTIFICATE_NAME_COLUMN_NAME));
        giftCertificate.setPrice(rs.getBigDecimal(GiftCertificateSqlColumnName.GIFT_CERTIFICATE_PRICE_COLUMN_NAME));
        giftCertificate.setCreateDate(rs.getTimestamp(
                GiftCertificateSqlColumnName.GIFT_CERTIFICATE_CREATE_DATE_COLUMN_NAME));
        giftCertificate.setLastUpdateDate(rs.getTimestamp(
                GiftCertificateSqlColumnName.GIFT_CERTIFICATE_LAST_UPDATE_DATE_COLUMN_NAME));
        giftCertificate.setDurationInDays(rs.getInt(GiftCertificateSqlColumnName.GIFT_CERTIFICATE_DURATION_COLUMN_NAME));
        giftCertificate.setDescription(rs.getString(GiftCertificateSqlColumnName.GIFT_CERTIFICATE_DESCRIPTION_COLUMN_NAME));
        return giftCertificate;
    }

}
