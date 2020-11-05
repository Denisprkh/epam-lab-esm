package com.epam.esm.dto.mapper.impl;


import com.epam.esm.dto.GiftCertificateDto;
import com.epam.esm.dto.mapper.DtoMapper;
import com.epam.esm.entity.GiftCertificate;
import com.epam.esm.entity.Tag;
import org.springframework.stereotype.Component;

import static java.util.Objects.nonNull;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.TimeZone;
import java.util.stream.Collectors;

@Component
public class GiftCertificateDtoMapper implements DtoMapper<GiftCertificateDto, GiftCertificate> {

    private static final String TIMEZONE = "UTC";
    private static final String DATE_FORMAT = "yyyy-MM-dd'T'HH:mm'Z'";


    @Override
    public GiftCertificateDto toDto(GiftCertificate giftCertificate) {
        GiftCertificateDto giftCertificateDto = new GiftCertificateDto();
        giftCertificateDto.setId(giftCertificate.getId());
        giftCertificateDto.setName(giftCertificate.getName());
        giftCertificateDto.setDescription(giftCertificate.getDescription());
        giftCertificateDto.setPrice(giftCertificate.getPrice());
        if (nonNull(giftCertificate.getTags())) {
            giftCertificateDto.setTags(giftCertificate.getTags().stream().map(Tag::getName).collect(Collectors.toList()));
        }
        giftCertificateDto.setCreateDate(convertTimesTampToISOFormat(giftCertificate.getCreateDate()));
        giftCertificateDto.setLastUpdateDate(convertTimesTampToISOFormat(giftCertificate.getLastUpdateDate()));
        giftCertificateDto.setDurationInDays(giftCertificate.getDurationInDays());
        return giftCertificateDto;
    }

    @Override
    public GiftCertificate toModel(GiftCertificateDto giftCertificateDto) {
        GiftCertificate giftCertificate = new GiftCertificate();
        giftCertificate.setId(giftCertificateDto.getId());
        giftCertificate.setName(giftCertificateDto.getName());
        giftCertificate.setDescription(giftCertificateDto.getDescription());
        giftCertificate.setPrice(giftCertificateDto.getPrice());
        if (nonNull(giftCertificateDto.getTags())) {
            giftCertificate.setTags(giftCertificateDto.getTags().stream().map(tagName -> new Tag(tagName)).collect
                    (Collectors.toList()));
        }
        giftCertificate.setDurationInDays(giftCertificateDto.getDurationInDays());
        return giftCertificate;
    }

    private String convertTimesTampToISOFormat(Timestamp timestamp) {
        TimeZone timeZone = TimeZone.getTimeZone(TIMEZONE);
        DateFormat dateFormat = new SimpleDateFormat(DATE_FORMAT);
        dateFormat.setTimeZone(timeZone);
        return dateFormat.format(timestamp);
    }
}
