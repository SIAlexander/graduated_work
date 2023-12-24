package ru.skypro.homework.dto.mapper;

import org.springframework.stereotype.Service;
import ru.skypro.homework.dto.LoginDto;
import ru.skypro.homework.model.User;

@Service
public class LoginMapping {
    public LoginDto mapToLoginDto(User entity) {
        LoginDto dto = new LoginDto();
        dto.setUserName(entity.getUserName());
        dto.setPassword(entity.getPassword());
        return dto;
    }

    public User mapToUser(LoginDto dto) {
        User entity = new User();
        entity.setUserName(dto.getUserName());
        entity.setPassword(dto.getPassword());
        return entity;
    }
}
