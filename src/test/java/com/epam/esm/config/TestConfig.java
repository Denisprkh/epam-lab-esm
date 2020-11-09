package com.epam.esm.config;

import com.epam.esm.dao.giftcertificate.GiftCertificateDao;
import com.epam.esm.dao.giftcertificate.GiftCertificateMapper;
import com.epam.esm.dao.giftcertificate.impl.GiftCertificateDaoImpl;
import com.epam.esm.dao.tag.TagDao;
import com.epam.esm.dao.tag.TagMapper;
import com.epam.esm.dao.tag.impl.TagDaoImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;

import javax.sql.DataSource;

@Configuration
public class TestConfig {

    private static final String SCRIPT_ENCODING = "UTF-8";

    @Bean
    public DataSource h2DataSource() {
        return new EmbeddedDatabaseBuilder()
                .setType(EmbeddedDatabaseType.H2)
                .setScriptEncoding(SCRIPT_ENCODING)
                .addDefaultScripts()
                .build();
    }

    @Bean
    public TagMapper tagMapper() {
        return new TagMapper();
    }

    @Bean
    public TagDao tagDao(TagMapper tagMapper) {
        return new TagDaoImpl(jdbcTemplate(), tagMapper);
    }

    @Bean
    public JdbcTemplate jdbcTemplate() {
        return new JdbcTemplate(h2DataSource());
    }

    @Bean
    public GiftCertificateMapper giftCertificateMapper() {
        return new GiftCertificateMapper();
    }

    @Bean
    public GiftCertificateDao giftCertificateDao(GiftCertificateMapper giftCertificateMapper) {
        return new GiftCertificateDaoImpl(jdbcTemplate(), giftCertificateMapper);
    }
}
