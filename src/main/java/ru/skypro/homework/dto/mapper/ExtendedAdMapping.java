package ru.skypro.homework.dto.mapper;

import org.springframework.stereotype.Service;
import ru.skypro.homework.dto.ExtendedAdDto;
import ru.skypro.homework.model.Ad;

@Service
public class ExtendedAdMapping {
    public ExtendedAdDto mapToExtendedDto(Ad entity) {
        ExtendedAdDto dto = new ExtendedAdDto();
        dto.setPk(entity.getPk());
        dto.setAuthorFirstName(entity.getUser().getFirstName());
        dto.setAuthorLastName(entity.getUser().getLastName());
        dto.setDescription(entity.getDescription());
        dto.setEmail(entity.getUser().getUserName());
        dto.setImage("/images/" + entity.getPk());
        dto.setPhone(entity.getUser().getPhone());
        dto.setPrice(entity.getPrice());
        dto.setTitle(entity.getTitle());
        return dto;
    }

    public Ad mapToAd(ExtendedAdDto dto) {
        Ad entity = new Ad();
        entity.setPk(dto.getPk());
        entity.setDescription(dto.getDescription());
        entity.setImage(dto.getImage());
        entity.setPrice(dto.getPrice());
        entity.setTitle(dto.getTitle());
        return entity;
    }
}
