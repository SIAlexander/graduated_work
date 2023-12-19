package ru.skypro.homework.dto.mapper;

import org.springframework.stereotype.Service;
import ru.skypro.homework.dto.RegisterDto;
import ru.skypro.homework.model.User;

@Service
public class RegisterMapping {
    public RegisterDto mapToRegisterDto(User entity){
        RegisterDto dto = new RegisterDto();
        dto.setUserName(entity.getUserName());
        dto.setPassword(entity.getPassword());
        dto.setFirstName(entity.getFirstName());
        dto.setLastName(entity.getLastName());
        dto.setPhone(entity.getPhone());
        dto.setRole(entity.getRole());
        return dto;
    }

    public User mapToUser(RegisterDto dto){
        User entity = new User();
        entity.setUserName(dto.getUserName());
        entity.setPassword(dto.getPassword());
        entity.setFirstName(dto.getFirstName());
        entity.setLastName(dto.getLastName());
        entity.setPhone(dto.getPhone());
        entity.setRole(dto.getRole());
        return entity;
    }
}
