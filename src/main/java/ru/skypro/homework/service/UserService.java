package ru.skypro.homework.service;

import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.UpdateUser;
import ru.skypro.homework.dto.User;

import java.io.IOException;

public interface UserService {

    void setPassword(String currentPassword, String newPassword);

    User getInformationUser();

    UpdateUser setInformationUser(UpdateUser updateUser);

    void setAvatar(MultipartFile avatar) throws IOException;
}
