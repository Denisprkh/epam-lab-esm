package com.epam.esm.controller;

import com.epam.esm.dto.GiftCertificateDto;
import com.epam.esm.service.GiftCertificateService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/giftCertificates")
public class GiftCertificateController {

    private final GiftCertificateService giftCertificateService;

    public GiftCertificateController(GiftCertificateService giftCertificateService) {
        this.giftCertificateService = giftCertificateService;
    }

    @PostMapping
    public GiftCertificateDto createGiftCertificate(@RequestBody GiftCertificateDto giftCertificateDto) {
        return giftCertificateService.addGiftCertificate(giftCertificateDto);
    }

    @GetMapping(value = "/{id}")
    public GiftCertificateDto findGiftCertificateById(@PathVariable Integer id) {
        return giftCertificateService.findGiftCertificateById(id);
    }

    @DeleteMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteGiftCertificate(@PathVariable Integer id) {
        giftCertificateService.deleteGiftCertificate(id);
    }

    @PutMapping
    public GiftCertificateDto updateGiftCertificate(@RequestBody GiftCertificateDto giftCertificateDto) {
        return giftCertificateService.updateGiftCertificate(giftCertificateDto);
    }

    @GetMapping(value = "/all")
    public List<GiftCertificateDto> findAllGiftCertificates() {
        return giftCertificateService.findAllGiftCertificates();
    }

    @GetMapping
    public List<GiftCertificateDto> findGiftCertificatesByParams(@RequestParam Map<String, String> params) {
        return giftCertificateService.findGiftCertificates(params);
    }

}
