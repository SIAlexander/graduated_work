package ru.skypro.homework.dto.mapper;

import org.springframework.stereotype.Service;
import ru.skypro.homework.dto.CreateOrUpdateAdDto;
import ru.skypro.homework.model.Ad;

@Service
public class CreateOrUpdateAdMapping {
    public CreateOrUpdateAdDto mapToCreateOrUpdateAdDto(Ad entity) {
        CreateOrUpdateAdDto dto = new CreateOrUpdateAdDto();
        dto.setTitle(entity.getTitle());
        dto.setPrice(entity.getPrice());
        dto.setDescription(entity.getDescription());
        return dto;
    }

    public Ad mapToAd(CreateOrUpdateAdDto dto) {
        Ad entity = new Ad();
        entity.setTitle(dto.getTitle());
        entity.setPrice(dto.getPrice());
        entity.setDescription(dto.getDescription());
        return entity;
    }
}
