package ru.skypro.homework.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.Ad;
import ru.skypro.homework.dto.Ads;
import ru.skypro.homework.dto.CreateOrUpdateAd;
import ru.skypro.homework.dto.ExtendedAd;
import ru.skypro.homework.service.AdsService;

import java.io.IOException;
import java.util.List;

@Slf4j
@CrossOrigin(value = "http://localhost:3000")
@RestController
@RequiredArgsConstructor
@RequestMapping("/ads")
@Tag(name = "Объявления")
public class AdsController {

    private final AdsService adsService;

    @Operation(summary = "Получение всех объявлений")
    @GetMapping()
    public ResponseEntity<List<Ads>> getAllAds() {
        return ResponseEntity.ok(adsService.getAllAds());
    }

    @Operation(summary = "Добавление объявления")
    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Ad> setAd(@RequestBody CreateOrUpdateAd createOrUpdateAd,
                                    @RequestParam MultipartFile image) throws IOException {
        return ResponseEntity.ok(adsService.setAd(createOrUpdateAd, image));
    }

    @Operation(summary = "Получение информации об объявлении")
    @GetMapping("{id}")
    public ResponseEntity<ExtendedAd> getExtendedAd(@PathVariable Integer id) {
        return ResponseEntity.ok(adsService.getExtendedAd(id));
    }

    @Operation(summary = "Удаление объявления")
    @DeleteMapping("{id}")
    public ResponseEntity<Void> removeExtendedAd(@PathVariable Integer id) {
        adsService.removeExtendedAd(id);
        return ResponseEntity.ok().build();
    }

    @Operation(summary = "Обновление информации об объявлении")
    @PatchMapping("{id}")
    public ResponseEntity<Ad> updateAd(@PathVariable Integer id,
                                       @RequestBody CreateOrUpdateAd createOrUpdateAd) {
        return ResponseEntity.ok(adsService.updateAd(id, createOrUpdateAd));
    }

    @Operation(summary = "Получение объявлений авторизованного пользователя")
    @GetMapping("/me")
    public ResponseEntity<Ads> getUserAds() {
        return ResponseEntity.ok(adsService.getUserAds());
    }

    @Operation(summary = "Обновление картинки объявления")
    @PatchMapping(value = "{id}/image", consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
    public ResponseEntity<byte[]> updateImageAd(@PathVariable Integer id,
                                                @RequestBody MultipartFile image) throws IOException {
        adsService.updateImageAd(id, image);
        return ResponseEntity.ok().build();
    }
}
