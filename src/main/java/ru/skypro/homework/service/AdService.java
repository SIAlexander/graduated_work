package ru.skypro.homework.service;

import org.springframework.security.core.Authentication;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.AdDto;
import ru.skypro.homework.dto.AdsDto;
import ru.skypro.homework.dto.CreateOrUpdateAdDto;
import ru.skypro.homework.dto.ExtendedAdDto;

import java.io.IOException;

public interface AdService {

    AdsDto getAllAds();

    AdDto setAd(CreateOrUpdateAdDto createOrUpdateAdDto, MultipartFile image, Authentication authentication) throws IOException;

    ExtendedAdDto getExtendedAd(Integer id);

    void removeExtendedAd(Integer id);

    AdDto updateAd(Integer id, CreateOrUpdateAdDto createOrUpdateAdDto);

    AdsDto getAdsMe(Authentication authentication);

    byte[] updateImageAd(Integer id, MultipartFile image) throws IOException;

    byte[] getImage(Integer imageId) throws IOException;
}
