package com.epam.esm.controller;

import com.epam.esm.entity.GiftCertificate;
import com.epam.esm.service.GiftCertificateService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/giftCertificates")
public class GiftCertificateController {

    private final GiftCertificateService giftCertificateService;

    public GiftCertificateController(GiftCertificateService giftCertificateService) {
        this.giftCertificateService = giftCertificateService;
    }

    @PostMapping
    public GiftCertificate createGiftCertificate(@RequestBody GiftCertificate giftCertificate) {
        return giftCertificateService.addGiftCertificateAndItsTags(giftCertificate);
    }

    @GetMapping(value = "/{id}")
    public GiftCertificate findGiftCertificateById(@PathVariable Integer id) {
        return giftCertificateService.findGiftCertificateById(id);
    }

    @DeleteMapping(value = "/{id}")
    public void deleteGiftCertificate(@PathVariable Integer id) {
        giftCertificateService.deleteGiftCertificate(id);
    }

    @PutMapping
    public GiftCertificate updateGiftCertificate(@RequestBody GiftCertificate giftCertificate) {
        return giftCertificateService.updateGiftCertificate(giftCertificate);
    }

    @GetMapping
    public List<GiftCertificate> findAllGiftCertificates() {
        return giftCertificateService.findAllGiftCertificates();
    }
}
