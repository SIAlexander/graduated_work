package ru.skypro.homework.service;

import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.Ad;
import ru.skypro.homework.dto.Ads;
import ru.skypro.homework.dto.CreateOrUpdateAd;
import ru.skypro.homework.dto.ExtendedAd;

import java.io.IOException;
import java.util.List;

public interface AdsService {

    List<Ads> getAllAds();

    Ad setAd(CreateOrUpdateAd createOrUpdateAd, MultipartFile image) throws IOException;

    ExtendedAd getExtendedAd(Integer id);

    void removeExtendedAd(Integer id);

    Ad updateAd(Integer id, CreateOrUpdateAd createOrUpdateAd);

    Ads getUserAds();

    byte[] updateImageAd(Integer id, MultipartFile image) throws IOException;

}
