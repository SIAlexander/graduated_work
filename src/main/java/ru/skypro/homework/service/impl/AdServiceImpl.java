package ru.skypro.homework.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.AdDto;
import ru.skypro.homework.dto.AdsDto;
import ru.skypro.homework.dto.CreateOrUpdateAdDto;
import ru.skypro.homework.dto.ExtendedAdDto;
import ru.skypro.homework.dto.mapper.AdMapping;
import ru.skypro.homework.dto.mapper.CreateOrUpdateAdMapping;
import ru.skypro.homework.dto.mapper.ExtendedAdMapping;
import ru.skypro.homework.model.Ad;
import ru.skypro.homework.model.Comment;
import ru.skypro.homework.model.User;
import ru.skypro.homework.repository.AdRepository;
import ru.skypro.homework.repository.CommentRepository;
import ru.skypro.homework.service.AdService;
import ru.skypro.homework.service.UserService;
import ru.skypro.homework.service.WorkingFile;

import java.io.*;
import java.nio.file.Path;
import java.util.*;
import java.util.stream.Collectors;

@Slf4j
@Service
public class AdServiceImpl implements AdService {
    @Value("${ad.images.dir.path}")
    private String adImagesDir;
    private final AdRepository adRepository;
    private final UserService userService;
    private final CreateOrUpdateAdMapping createOrUpdateAdMapping;
    private final AdMapping adMapping;
    private final ExtendedAdMapping extendedAdMapping;
    private final CommentRepository commentRepository;
    private final WorkingFile workingFile;

    public AdServiceImpl(AdRepository adRepository, UserService userService, CreateOrUpdateAdMapping createOrUpdateAdMapping, AdMapping adMapping, ExtendedAdMapping extendedAdMapping, CommentRepository commentRepository, WorkingFile workingFile) {
        this.adRepository = adRepository;
        this.userService = userService;
        this.createOrUpdateAdMapping = createOrUpdateAdMapping;
        this.adMapping = adMapping;
        this.extendedAdMapping = extendedAdMapping;
        this.commentRepository = commentRepository;
        this.workingFile = workingFile;
    }

    /**
     * Метод возвращает список всех объявлений
     *
     * @return AdsDto
     */
    @Override
    public AdsDto getAllAds() {
        List<Ad> adList = adRepository.findAll();
        List<AdDto> adDtoList = adList.stream()
                .map(adMapping::mapToAdDto)
                .collect(Collectors.toList());
        return new AdsDto(adList.size(), adDtoList);
    }

    /**
     * Метод добавления объявлений
     *
     * @param createOrUpdateAdDto
     * @param image
     * @param authentication
     * @return AdDto
     * @throws IOException
     */
    @Override
    public AdDto setAd(CreateOrUpdateAdDto createOrUpdateAdDto, MultipartFile image, Authentication authentication) throws IOException {
        User user = userService.getUser(authentication.getName());
        Ad ad = createOrUpdateAdMapping.mapToAd(createOrUpdateAdDto);
        ad.setUser(user);
        ad.setImage(workingFile.getFilePath(adImagesDir, image).toString());
        ad.setFileSize(image.getSize());
        ad.setMediaType(image.getContentType());
        ad.setData(image.getBytes());
        adRepository.save(ad);
        return adMapping.mapToAdDto(ad);
    }

    /**
     * Метод по получению информации об объявлении
     *
     * @param id
     * @return ExtendedAdDto
     */
    @Override
    public ExtendedAdDto getExtendedAd(Integer id) {
        Ad ad = adRepository.findByPk(id);
        if (ad == null) {
            return null;
        }
        return extendedAdMapping.mapToExtendedDto(ad);
    }

    /**
     * Метод удаления объявления
     *
     * @param id
     */
    @Override
    public void removeExtendedAd(Integer id) {
        List<Comment> commentList = commentRepository.findByAdPk(id);
        for (int i = 0; i <= commentList.size() - 1; i++) {
            commentRepository.delete(commentList.get(i));
        }
        Ad ad = adRepository.findByPk(id);
        adRepository.delete(ad);
    }

    /**
     * Метод обновления информации об объявлении
     *
     * @param id
     * @param createOrUpdateAdDto
     * @return AdDto
     */
    @Override
    public AdDto updateAd(Integer id, CreateOrUpdateAdDto createOrUpdateAdDto) {
        Ad ad = adRepository.findByPk(id);
        if (ad == null) {
            return null;
        }
        ad.setTitle(createOrUpdateAdDto.getTitle());
        ad.setPrice(createOrUpdateAdDto.getPrice());
        ad.setDescription(createOrUpdateAdDto.getDescription());
        adRepository.save(ad);
        return adMapping.mapToAdDto(ad);
    }

    /**
     * Метод получения объявлений авторизованного пользователя
     *
     * @param authentication
     * @return AdsDto
     */
    @Override
    public AdsDto getAdsMe(Authentication authentication) {
        User user = userService.getUser(authentication.getName());
        List<AdDto> adList = adRepository.findByUser(user)
                .stream()
                .map(adMapping::mapToAdDto)
                .collect(Collectors.toList());
        return new AdsDto(adList.size(), adList);
    }

    /**
     * Метод обновления картинки объявления
     *
     * @param id
     * @param image
     * @return byte[]
     * @throws IOException
     */
    @Override
    public byte[] updateImageAd(Integer id, MultipartFile image) throws IOException {
        Ad ad = adRepository.findByPk(id);
        if (ad == null) {
            return null;
        }
        Path filePath = Path.of(adImagesDir, image.getOriginalFilename() + "_"
                + image.getSize() + "." + getExtension(image.getOriginalFilename()));
        byte[] data = image.getBytes();
        ad.setImage(filePath.toString());
        ad.setData(data);
        adRepository.save(ad);
        return ad.getData();
    }

    /**
     * Метод получения изображения
     *
     * @param imageId
     * @return byte[]
     * @throws IOException
     */
    @Override
    public byte[] getImage(Integer imageId) throws IOException {
        return adRepository.findByPk(imageId).getData();
    }

    /**
     * Метод преобразования имени файла
     *
     * @param fileName
     * @return String
     */
    private String getExtension(String fileName) {
        return fileName.substring(fileName.lastIndexOf(".") + 1);
    }
}
