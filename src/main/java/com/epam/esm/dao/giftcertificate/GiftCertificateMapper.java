package com.epam.esm.dao.giftcertificate;

import com.epam.esm.entity.GiftCertificate;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;

public class GiftCertificateMapper implements RowMapper<GiftCertificate> {

    @Override
    public GiftCertificate mapRow(ResultSet rs, int rowNum) throws SQLException {
        GiftCertificate giftCertificate = new GiftCertificate();
        giftCertificate.setId(rs.getInt(GiftCertificateSqlColumnName.GIFT_CERTIFICATE_ID_COLUMN_NAME));
        giftCertificate.setName(rs.getString(GiftCertificateSqlColumnName.GIFT_CERTIFICATE_NAME_COLUMN_NAME));
        giftCertificate.setPrice(rs.getBigDecimal(GiftCertificateSqlColumnName.GIFT_CERTIFICATE_PRICE_COLUMN_NAME));
        giftCertificate.setCreateDate(extractLocaleDateTimeFromResultSet
                (rs, GiftCertificateSqlColumnName.GIFT_CERTIFICATE_CREATE_DATE_COLUMN_NAME));
        giftCertificate.setLastUpdateDate(extractLocaleDateTimeFromResultSet(rs,
                GiftCertificateSqlColumnName.GIFT_CERTIFICATE_LAST_UPDATE_DATE_COLUMN_NAME));
        giftCertificate.setDurationInDays(rs.getInt(GiftCertificateSqlColumnName.GIFT_CERTIFICATE_DURATION_COLUMN_NAME));
        giftCertificate.setDescription(rs.getString(GiftCertificateSqlColumnName.GIFT_CERTIFICATE_DESCRIPTION_COLUMN_NAME));
        return giftCertificate;
    }

    private LocalDateTime extractLocaleDateTimeFromResultSet(ResultSet resultSet, String columnName) throws SQLException {
        return new Timestamp(resultSet.getTimestamp(columnName).getTime()).toLocalDateTime();
    }
}
