package ru.skypro.homework.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.Ad;
import ru.skypro.homework.dto.Ads;
import ru.skypro.homework.dto.CreateOrUpdateAd;
import ru.skypro.homework.dto.ExtendedAd;
import ru.skypro.homework.service.AdsService;

import java.io.IOException;
import java.util.List;

@Service
public class AdsServiceImpl implements AdsService {

    @Override
    public List<Ads> getAllAds() {
        return null;
    }

    @Override
    public Ad setAd(CreateOrUpdateAd createOrUpdateAd, MultipartFile image) throws IOException {
        return null;
    }

    @Override
    public ExtendedAd getExtendedAd(Integer id) {
        return null;
    }

    @Override
    public void removeExtendedAd(Integer id) {

    }

    @Override
    public Ad updateAd(Integer id, CreateOrUpdateAd createOrUpdateAd) {
        return null;
    }

    @Override
    public Ads getUserAds() {
        return null;
    }

    @Override
    public byte[] updateImageAd(Integer id, MultipartFile image) throws IOException{
        return null;
    }

}
