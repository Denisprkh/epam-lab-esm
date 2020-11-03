package com.epam.esm.dao.giftcertificate.impl;

import com.epam.esm.entity.GiftCertificate;
import com.epam.esm.entity.Tag;
import com.epam.esm.dao.giftcertificate.GiftCertificateMapper;
import com.epam.esm.dao.giftcertificate.GiftCertificateDao;
import com.epam.esm.dao.giftcertificate.GiftCertificateSqlQuery;
import com.epam.esm.exception.ResourceNotFoundException;
import com.epam.esm.util.ResourceBundleErrorMessage;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Component
public class GiftCertificateDaoImpl implements GiftCertificateDao {

    private final DataSource dataSource;
    private final JdbcTemplate jdbcTemplate;
    private final GiftCertificateMapper giftCertificateMapper;
    private static final int SUCCESSFULLY_UPDATED_ROW = 1;

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
        try {
            return jdbcTemplate.queryForObject(GiftCertificateSqlQuery.FIND_GIFT_CERTIFICATE_BY_ID,
                    giftCertificateMapper, id);
        } catch (EmptyResultDataAccessException e) {
            throw new ResourceNotFoundException(ResourceBundleErrorMessage.RESOURCE_NOT_FOUND);
        }
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
                    PreparedStatement statement = connection.prepareStatement(GiftCertificateSqlQuery.UPDATE_CERTIFICATE_BY_ID);
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
        try {
            return jdbcTemplate.query(GiftCertificateSqlQuery.FIND_ALL_GIFT_CERTIFICATES, giftCertificateMapper);
        } catch (EmptyResultDataAccessException e) {
            throw new ResourceNotFoundException(ResourceBundleErrorMessage.RESOURCE_NOT_FOUND);
        }
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
        try {
            return jdbcTemplate.query(GiftCertificateSqlQuery.SELECT_ALL_FIELDS + condition, giftCertificateMapper);
        } catch (EmptyResultDataAccessException e) {
            throw new ResourceNotFoundException(ResourceBundleErrorMessage.RESOURCE_NOT_FOUND);
        }
    }

    @Override
    public Optional<GiftCertificate> findByName(String name) {
        try {
            return Optional.ofNullable(jdbcTemplate.queryForObject(GiftCertificateSqlQuery.FIND_GIFT_CERTIFICATE_BY_NAME,
                    giftCertificateMapper, name));
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }
}
