package ru.skypro.homework.dto.mapper;

import org.springframework.stereotype.Service;
import ru.skypro.homework.dto.AdDto;
import ru.skypro.homework.model.Ad;

@Service
public class AdMapping {
    public AdDto mapToAdDto(Ad entity) {
        AdDto dto = new AdDto();
        dto.setAuthor(entity.getUser().getId());
        dto.setImage(entity.getImage());
        dto.setPk(entity.getPk());
        dto.setPrice(entity.getPrice());
        dto.setTitle(entity.getTitle());
        return dto;
    }

    public Ad mapToAd(AdDto dto) {
        Ad entity = new Ad();
        entity.setImage(dto.getImage());
        entity.setPk(dto.getPk());
        entity.setPrice(dto.getPrice());
        entity.setTitle(dto.getTitle());
        return entity;
    }
}
