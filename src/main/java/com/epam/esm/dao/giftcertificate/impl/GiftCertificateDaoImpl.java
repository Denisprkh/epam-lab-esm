package com.epam.esm.dao.giftcertificate.impl;

import com.epam.esm.entity.GiftCertificate;
import com.epam.esm.entity.Tag;
import com.epam.esm.dao.giftcertificate.GiftCertificateMapper;
import com.epam.esm.dao.giftcertificate.GiftCertificateDao;
import com.epam.esm.dao.giftcertificate.GiftCertificateSqlQuery;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;
import java.util.Objects;

@Component
public class GiftCertificateDaoImpl implements GiftCertificateDao {

    private final DataSource dataSource;
    private final JdbcTemplate jdbcTemplate;
    private final GiftCertificateMapper giftCertificateMapper;
    private static final int SUCCESSFULLY_UPDATED_ROW = 1;
    private static final Logger LOG = LogManager.getLogger();

    public GiftCertificateDaoImpl(DataSource dataSource, GiftCertificateMapper giftCertificateMapper) {
        this.dataSource = dataSource;
        this.jdbcTemplate = new JdbcTemplate(this.dataSource);
        this.giftCertificateMapper = giftCertificateMapper;
    }

    @Override
    public GiftCertificate create(GiftCertificate giftCertificate) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(connection -> {
            PreparedStatement statement = connection.prepareStatement(GiftCertificateSqlQuery.ADD_GIFT_CERTIFICATE,
                    Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, giftCertificate.getName());
            statement.setBigDecimal(2, giftCertificate.getPrice());
            statement.setTimestamp(3, giftCertificate.getCreateDate());
            statement.setTimestamp(4, giftCertificate.getLastUpdateDate());
            statement.setInt(5, giftCertificate.getDurationInDays());
            statement.setString(6, giftCertificate.getDescription());
            return statement;
        }, keyHolder);
        giftCertificate.setId(Objects.requireNonNull(keyHolder.getKey()).intValue());
        return giftCertificate;
    }

    @Override
    public GiftCertificate findById(Integer id) {
        return jdbcTemplate.queryForObject(GiftCertificateSqlQuery.FIND_GIFT_CERTIFICATE_BY_ID,
                giftCertificateMapper, id);
    }

    @Override
    public boolean delete(Integer id) {
        return jdbcTemplate.update(GiftCertificateSqlQuery.DELETE_GIFT_CERTIFICATE_BY_ID, id) == SUCCESSFULLY_UPDATED_ROW;
    }

    @Override
    public boolean deleteGiftCertificatesTags(Integer giftCertificateId) {
        return jdbcTemplate.update(GiftCertificateSqlQuery.DELETE_GIFT_CERTIFICATES_TAGS, giftCertificateId) ==
                SUCCESSFULLY_UPDATED_ROW;
    }

    @Override
    public GiftCertificate update(GiftCertificate giftCertificate) {
        jdbcTemplate.update(connection -> {
                    PreparedStatement statement = connection.prepareStatement(GiftCertificateSqlQuery.UPDATE_TAG_BY_ID);
                    statement.setString(1, giftCertificate.getName());
                    statement.setBigDecimal(2, giftCertificate.getPrice());
                    statement.setTimestamp(3, giftCertificate.getLastUpdateDate());
                    statement.setInt(4, giftCertificate.getDurationInDays());
                    statement.setString(5, giftCertificate.getDescription());
                    statement.setInt(6, giftCertificate.getId());
                    return statement;
                }
        );
        return giftCertificate;
    }

    @Override
    public List<GiftCertificate> findAll() {
        return jdbcTemplate.query(GiftCertificateSqlQuery.FIND_ALL_GIFT_CERTIFICATES, giftCertificateMapper);
    }

    @Override
    public void addGiftCertificatesTags(List<Tag> giftCertificatesTags, GiftCertificate giftCertificate) {
        giftCertificatesTags.forEach(tag -> jdbcTemplate.update(connection -> {
            PreparedStatement statement = connection.prepareStatement(GiftCertificateSqlQuery.ADD_GIFT_CERTIFICATES_TAG);
            statement.setInt(1, tag.getId());
            statement.setInt(2, giftCertificate.getId());
            return statement;
        }));
    }

    @Override
    public List<GiftCertificate> findGiftCertificatesByParameters(String condition) {
        return jdbcTemplate.query(GiftCertificateSqlQuery.SELECT_ALL_FIELDS + condition, giftCertificateMapper);
    }
}
