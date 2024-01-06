package ru.skypro.homework.dto.mapper;

import org.springframework.stereotype.Service;
import ru.skypro.homework.dto.LoginDto;
import ru.skypro.homework.model.User;

@Service
public class LoginMapping {
    public LoginDto mapToLoginDto(User entity) {
        LoginDto dto = new LoginDto();
        dto.setUsername(entity.getUserName());
        dto.setPassword(entity.getPassword());
        return dto;
    }

    public User mapToUser(LoginDto dto) {
        User entity = new User();
        entity.setUserName(dto.getUsername());
        entity.setPassword(dto.getPassword());
        return entity;
    }
}
