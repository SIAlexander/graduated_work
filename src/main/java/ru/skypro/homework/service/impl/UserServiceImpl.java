package ru.skypro.homework.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.UpdateUserDto;
import ru.skypro.homework.dto.UserDto;
import ru.skypro.homework.service.UserService;

import java.io.IOException;

@Service
public class UserServiceImpl implements UserService {

    @Override
    public void setPassword(String currentPassword, String newPassword) {

    }
    @Override
    public UserDto getInformationUser() {
        return null;
    }

    @Override
    public UpdateUserDto setInformationUser(UpdateUserDto updateUserDto){
        return null;
    }

    @Override
    public void setAvatar(MultipartFile avatar) throws IOException {

    }
}
