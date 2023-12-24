package ru.skypro.homework.service;

import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.AdDto;
import ru.skypro.homework.dto.AdsDto;
import ru.skypro.homework.dto.CreateOrUpdateAdDto;
import ru.skypro.homework.dto.ExtendedAdDto;

import java.io.IOException;
import java.util.List;

public interface AdService {

    List<AdsDto> getAllAds();

    AdDto setAd(CreateOrUpdateAdDto createOrUpdateAdDto, MultipartFile image) throws IOException;

    ExtendedAdDto getExtendedAd(Integer id);

    void removeExtendedAd(Integer id);

    AdDto updateAd(Integer id, CreateOrUpdateAdDto createOrUpdateAdDto);

    AdsDto getUserAds();

    byte[] updateImageAd(Integer id, MultipartFile image) throws IOException;

}
