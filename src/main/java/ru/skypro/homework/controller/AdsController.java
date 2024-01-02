package ru.skypro.homework.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.AdDto;
import ru.skypro.homework.dto.AdsDto;
import ru.skypro.homework.dto.CreateOrUpdateAdDto;
import ru.skypro.homework.dto.ExtendedAdDto;
import ru.skypro.homework.service.AdService;

import java.io.IOException;

@Slf4j
@CrossOrigin(value = "http://localhost:3000")
@RestController
@RequiredArgsConstructor
@RequestMapping("/ads")
@Tag(name = "Объявления")
public class AdsController {

    private final AdService adService;

    @Operation(summary = "Получение всех объявлений")
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<AdsDto> getAllAds() {
        return ResponseEntity.ok(adService.getAllAds());
    }

    @Operation(summary = "Добавление объявления")
    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<AdDto> setAd(@RequestPart(value = "properties") CreateOrUpdateAdDto createOrUpdateAdDto,
                                       @RequestPart(value = "image") MultipartFile image,
                                       Authentication authentication) throws IOException {
        if (authentication.getName() != null) {
            adService.setAd(createOrUpdateAdDto, image, authentication);
            return ResponseEntity.status(HttpStatus.CREATED).build();

        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

    }

    @Operation(summary = "Получение информации об объявлении")
    @GetMapping(value = "{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ExtendedAdDto> getExtendedAd(@PathVariable Integer id, Authentication authentication) {
        if (adService.getExtendedAd(id) == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        } else if (authentication.getName() != null) {
            return ResponseEntity.ok(adService.getExtendedAd(id));
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }

    @Operation(summary = "Удаление объявления")
    @DeleteMapping("{id}")
    public ResponseEntity<Void> removeExtendedAd(@PathVariable Integer id, Authentication authentication) {
        if (adService.getExtendedAd(id) == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        } else if (authentication.getName() != null) {
            adService.removeExtendedAd(id);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }

    @Operation(summary = "Обновление информации об объявлении")
    @PatchMapping("{id}")
    public ResponseEntity<AdDto> updateAd(@PathVariable Integer id,
                                          @RequestBody CreateOrUpdateAdDto createOrUpdateAdDto,
                                          Authentication authentication) {
        if (adService.updateAd(id, createOrUpdateAdDto) == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        } else if (authentication.getName() != null) {
            return ResponseEntity.ok(adService.updateAd(id, createOrUpdateAdDto));
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }

    @Operation(summary = "Получение объявлений авторизованного пользователя")
    @GetMapping(value = "/me", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<AdsDto> getAdsMe(Authentication authentication) {
        if (authentication.getName() != null) {
            return ResponseEntity.ok(adService.getAdsMe(authentication));
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }

    @Operation(summary = "Обновление картинки объявления")
    @Secured("ROLE_ADMIN")
    @PatchMapping(value = "{id}/image",
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE,
            produces = MediaType.APPLICATION_OCTET_STREAM_VALUE
    )
    public ResponseEntity<byte[]> updateImageAd(@PathVariable Integer id,
                                                @RequestBody MultipartFile image,
                                                Authentication authentication) throws IOException {
        if (adService.updateImageAd(id, image) == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        } else if (authentication.getName() != null) {
            return ResponseEntity.ok(adService.updateImageAd(id, image));
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }
}
