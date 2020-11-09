package com.epam.esm.controller;

import com.epam.esm.dto.GiftCertificateDto;
import com.epam.esm.service.GiftCertificateService;
import com.epam.esm.validation.ValidationGroup.Update;
import com.epam.esm.validation.ValidationGroup.Create;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * Controller used to manipulate CRUD operations on
 * {@code GiftCertificate} data
 */
@RestController
@RequestMapping("/giftCertificates")
public class GiftCertificateController {

    private final GiftCertificateService giftCertificateService;

    public GiftCertificateController(GiftCertificateService giftCertificateService) {
        this.giftCertificateService = giftCertificateService;
    }

    /**
     * Creates new {@code GiftCertificate} in the database.
     * If new tags are passed during creation, they are
     * added to the database as well.
     * If resource is already exists {@code HttpStatus.BAD_REQUEST} is returned.
     *
     * @param giftCertificateDto {@code GiftCertificateDto} with values for {@code GiftCertificate} creation.
     * @return Created gift certificate.
     */
    @PostMapping
    public GiftCertificateDto createGiftCertificate(@Validated(Create.class) @RequestBody GiftCertificateDto giftCertificateDto) {
        return giftCertificateService.addGiftCertificate(giftCertificateDto);
    }

    /**
     * Returns {@code GiftCertificateDto} with the requested id.
     * If no resource found {@code HttpStatus.NOT_FOUND} is returned.
     *
     * @param id id of the requested certificate.
     * @return GiftCertificateDTO with the requested id.
     */
    @GetMapping(value = "/{id}")
    public GiftCertificateDto findGiftCertificateById(@PathVariable Integer id) {
        return giftCertificateService.findGiftCertificateById(id);
    }

    /**
     * Deletes gift certificate with the specified id.
     *
     * @param id {@code GiftCertificate}'s id.
     */
    @DeleteMapping(value = "/{id}")
    public void deleteGiftCertificate(@PathVariable Integer id) {
        giftCertificateService.deleteGiftCertificate(id);
    }

    /**
     * Updates {@code GiftCertificate}
     * If resource with updated name is already exists {@code HttpStatus.BAD_REQUEST} is returned.
     *
     * @param giftCertificateDto {@code GiftCertificateDto} with updated values.
     * @param id                 {@code GiftCertificate}'s id which should be updated.
     * @return {@code GiftCertificateDto} with updated values.
     */
    @PutMapping(value = "/{id}")
    public GiftCertificateDto updateGiftCertificate(@Validated(Update.class) @RequestBody GiftCertificateDto
                                                            giftCertificateDto, @PathVariable Integer id) {
        return giftCertificateService.updateGiftCertificate(giftCertificateDto, id);
    }

    /**
     * Finds all gift certificates according to parameters.
     *
     * @param params Parameters for building a search query.
     * @return List of {@code GiftCertificateDto}
     */
    @GetMapping
    public List<GiftCertificateDto> findGiftCertificatesByParams(@RequestParam Map<String, String> params) {
        return giftCertificateService.findGiftCertificates(params);
    }

}
