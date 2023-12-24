package ru.skypro.homework.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.AdDto;
import ru.skypro.homework.dto.AdsDto;
import ru.skypro.homework.dto.CreateOrUpdateAdDto;
import ru.skypro.homework.dto.ExtendedAdDto;
import ru.skypro.homework.dto.mapper.AdMapping;
import ru.skypro.homework.repository.AdRepository;
import ru.skypro.homework.service.AdService;

import java.io.IOException;
import java.nio.file.Path;
import java.util.List;

@Service
public class AdServiceImpl implements AdService {

    @Override
    public List<AdsDto> getAllAds() {
        return null;
    }

    @Override
    public AdDto setAd(CreateOrUpdateAdDto createOrUpdateAdDto, MultipartFile image) throws IOException {
        return null;
    }

    @Override
    public ExtendedAdDto getExtendedAd(Integer id) {
        return null;
    }

    @Override
    public void removeExtendedAd(Integer id) {

    }

    @Override
    public AdDto updateAd(Integer id, CreateOrUpdateAdDto createOrUpdateAdDto) {
        return null;
    }

    @Override
    public AdsDto getUserAds() {
        return null;
    }

    @Override
    public byte[] updateImageAd(Integer id, MultipartFile image) throws IOException{
        return null;
    }

}
