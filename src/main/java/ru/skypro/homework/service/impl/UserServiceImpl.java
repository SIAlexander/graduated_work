package ru.skypro.homework.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.UpdateUser;
import ru.skypro.homework.dto.User;
import ru.skypro.homework.service.UserService;

import java.io.IOException;

@Service
public class UserServiceImpl implements UserService {

    @Override
    public void setPassword(String currentPassword, String newPassword) {

    }
    @Override
    public User getInformationUser() {
        return null;
    }

    @Override
    public UpdateUser setInformationUser(UpdateUser updateUser){
        return null;
    }

    @Override
    public void setAvatar(MultipartFile avatar) throws IOException {

    }
}
