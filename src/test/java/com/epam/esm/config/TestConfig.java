package com.epam.esm.config;

import com.epam.esm.dao.giftcertificate.GiftCertificateDao;
import com.epam.esm.dao.giftcertificate.GiftCertificateMapper;
import com.epam.esm.dao.giftcertificate.impl.GiftCertificateDaoImpl;
import com.epam.esm.dao.tag.TagDao;
import com.epam.esm.dao.tag.TagMapper;
import com.epam.esm.dao.tag.impl.TagDaoImpl;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;

import javax.sql.DataSource;

@Configuration
@Import({TagDaoImpl.class})
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
    public TagMapper getTagMapper() {
        return new TagMapper();
    }

    @Bean
    public TagDao getTagDaoTest(TagMapper tagMapper, @Qualifier("h2DataSource") DataSource dataSource) {
        return new TagDaoImpl(dataSource, tagMapper);
    }

    @Bean
    public GiftCertificateMapper getGiftCertificateMapper() {
        return new GiftCertificateMapper();
    }

    @Bean
    public GiftCertificateDao getGiftCertificateDaoTest(GiftCertificateMapper giftCertificateMapper,
                                                        @Qualifier("h2DataSource") DataSource dataSource) {
        return new GiftCertificateDaoImpl(dataSource, giftCertificateMapper);
    }
}
