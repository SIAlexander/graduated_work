package ru.skypro.homework.service;

import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.UpdateUserDto;
import ru.skypro.homework.dto.UserDto;

import java.io.IOException;

public interface UserService {

    void setPassword(String currentPassword, String newPassword);

    UserDto getInformationUser();

    UpdateUserDto setInformationUser(UpdateUserDto updateUserDto);

    void setAvatar(MultipartFile avatar) throws IOException;
}
