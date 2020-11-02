package com.epam.esm.dto.mapper;

import com.epam.esm.dto.GiftCertificateDto;
import com.epam.esm.entity.GiftCertificate;
import com.epam.esm.entity.Tag;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Component
public class GiftCertificateDtoMapper {

    private ModelMapper mapper;

    public GiftCertificateDtoMapper(ModelMapper mapper) {
        this.mapper = mapper;
        configureModelToDtoConverting();
    }


    private void configureModelToDtoConverting() {
        TypeMap<GiftCertificate, GiftCertificateDto> typeMap = mapper.createTypeMap
                (GiftCertificate.class, GiftCertificateDto.class);
        Converter<List<Tag>, List<String>> tagToStringConverter = tagList -> tagList.getSource().stream().map
                (Tag::getName).collect(Collectors.toList());
        typeMap.addMappings(map ->
                map.using(tagToStringConverter).map(GiftCertificate::getTags, GiftCertificateDto::setTags)
        );
    }

    public GiftCertificate toModel(GiftCertificateDto giftCertificateDto) {
        return Objects.isNull(giftCertificateDto) ? null : mapper.map(giftCertificateDto, GiftCertificate.class);
    }

    public GiftCertificateDto toDto(GiftCertificate model) {

        return Objects.isNull(model) ? null : mapper.map(model, GiftCertificateDto.class);
    }

}
