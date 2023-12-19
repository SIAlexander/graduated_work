package ru.skypro.homework.dto.mapper;

import org.springframework.stereotype.Service;
import ru.skypro.homework.dto.UpdateUserDto;
import ru.skypro.homework.model.User;

@Service
public class UpdateUserMapping {
    public UpdateUserDto mapToUpdateUserDto(User entity) {
        UpdateUserDto dto = new UpdateUserDto();
        dto.setFirstName(entity.getFirstName());
        dto.setLastName(entity.getLastName());
        dto.setPhone(entity.getPhone());
        return dto;
    }

    public User mapToUser(UpdateUserDto dto) {
        User entity = new User();
        entity.setFirstName(dto.getFirstName());
        entity.setLastName(dto.getLastName());
        entity.setPhone(dto.getPhone());
        return entity;
    }
}
