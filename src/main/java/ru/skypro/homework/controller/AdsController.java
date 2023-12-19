package ru.skypro.homework.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.AdDto;
import ru.skypro.homework.dto.AdsDto;
import ru.skypro.homework.dto.CreateOrUpdateAdDto;
import ru.skypro.homework.dto.ExtendedAdDto;
import ru.skypro.homework.service.AdService;

import java.io.IOException;
import java.util.List;

@Slf4j
@CrossOrigin(value = "http://localhost:3000")
@RestController
@RequiredArgsConstructor
@RequestMapping("/ads")
@Tag(name = "Объявления")
public class AdsController {

    private final AdService adService;

    @Operation(summary = "Получение всех объявлений")
    @GetMapping()
    public ResponseEntity<List<AdsDto>> getAllAds() {
        return ResponseEntity.ok(adService.getAllAds());
    }

    @Operation(summary = "Добавление объявления")
    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<AdDto> setAd(@RequestBody CreateOrUpdateAdDto createOrUpdateAdDto,
                                       @RequestParam MultipartFile image) throws IOException {
        return ResponseEntity.ok(adService.setAd(createOrUpdateAdDto, image));
    }

    @Operation(summary = "Получение информации об объявлении")
    @GetMapping("{id}")
    public ResponseEntity<ExtendedAdDto> getExtendedAd(@PathVariable Integer id) {
        return ResponseEntity.ok(adService.getExtendedAd(id));
    }

    @Operation(summary = "Удаление объявления")
    @DeleteMapping("{id}")
    public ResponseEntity<Void> removeExtendedAd(@PathVariable Integer id) {
        adService.removeExtendedAd(id);
        return ResponseEntity.ok().build();
    }

    @Operation(summary = "Обновление информации об объявлении")
    @PatchMapping("{id}")
    public ResponseEntity<AdDto> updateAd(@PathVariable Integer id,
                                          @RequestBody CreateOrUpdateAdDto createOrUpdateAdDto) {
        return ResponseEntity.ok(adService.updateAd(id, createOrUpdateAdDto));
    }

    @Operation(summary = "Получение объявлений авторизованного пользователя")
    @GetMapping("/me")
    public ResponseEntity<AdsDto> getUserAds() {
        return ResponseEntity.ok(adService.getUserAds());
    }

    @Operation(summary = "Обновление картинки объявления")
    @PatchMapping(value = "{id}/image", consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
    public ResponseEntity<byte[]> updateImageAd(@PathVariable Integer id,
                                                @RequestBody MultipartFile image) throws IOException {
        adService.updateImageAd(id, image);
        return ResponseEntity.ok().build();
    }
}
