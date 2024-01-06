package ru.skypro.homework.service;

import org.springframework.security.core.Authentication;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.UpdateUserDto;
import ru.skypro.homework.dto.UserDto;
import ru.skypro.homework.model.User;

import java.io.IOException;

public interface UserService {

    void setPassword(String currentPassword, String newPassword, Authentication authentication);

    UserDto getInformationUser(Authentication authentication);

    UpdateUserDto setInformationUser(UpdateUserDto updateUserDto, Authentication authentication);

    void setAvatar(MultipartFile avatar, Authentication authentication) throws IOException;

    User getUser(String userName);

//    byte[] getImage(Authentication authentication) throws IOException;
    byte[] getImage(Integer id) throws IOException;
}

