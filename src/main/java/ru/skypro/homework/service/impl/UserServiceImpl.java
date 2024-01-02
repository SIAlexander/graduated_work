package ru.skypro.homework.service.impl;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.NewPasswordDto;
import ru.skypro.homework.dto.UpdateUserDto;
import ru.skypro.homework.dto.UserDto;
import ru.skypro.homework.dto.mapper.NewPasswordMapping;
import ru.skypro.homework.dto.mapper.UpdateUserMapping;
import ru.skypro.homework.dto.mapper.UserMapping;
import ru.skypro.homework.model.User;
import ru.skypro.homework.repository.UserRepository;
import ru.skypro.homework.service.UserService;

import java.io.*;

@Service
public class UserServiceImpl implements UserService {

    @Value("${avatar.images.dir.path}")
    private String avatarImagesDir;
    private final UserRepository userRepository;
    private final UserMapping userMapping;
    private final PasswordEncoder encoder;
    private final NewPasswordMapping newPasswordMapping;
    private final UpdateUserMapping updateUserMapping;
    private final WorkingFileImpl workingFile;


    public UserServiceImpl(UserRepository userRepository, UserMapping userMapping, PasswordEncoder encoder, NewPasswordMapping newPasswordMapping, UpdateUserMapping updateUserMapping, WorkingFileImpl workingFile) {
        this.userRepository = userRepository;
        this.userMapping = userMapping;
        this.encoder = encoder;
        this.newPasswordMapping = newPasswordMapping;
        this.updateUserMapping = updateUserMapping;
        this.workingFile = workingFile;
    }

    /**
     * Метод обновление пароля
     *
     * @param currentPassword
     * @param newPassword
     * @param authentication
     */
    @Override
    public void setPassword(String currentPassword, String newPassword, Authentication authentication) {
        if (authentication.getName() != null) {
            User user = userRepository.findByUserName(authentication.getName());
            NewPasswordDto newPasswordDto = newPasswordMapping.mapToCurrentPasswordDto(user);
            if (encoder.matches(currentPassword, newPasswordDto.getCurrentPassword())) {
                user.setPassword(encoder.encode(newPassword));
                userRepository.save(user);
            }
        }

    }

    /**
     * Метод получения информации об авторизованном пользователе
     *
     * @param authentication
     * @return UserDto
     */
    @Override
    public UserDto getInformationUser(Authentication authentication) {
        User user = getUser(authentication.getName());
        return userMapping.mapToUserDto(user);
    }

    /**
     * Метод обновления информации об авторизованном пользователе
     *
     * @param updateUserDto
     * @param authentication
     * @return UpdateUserDto
     */
    @Override
    public UpdateUserDto setInformationUser(UpdateUserDto updateUserDto, Authentication authentication) {
        if (authentication.getName() != null) {
            User user = userRepository.findByUserName(authentication.getName());
            user.setFirstName(updateUserDto.getFirstName());
            user.setLastName(updateUserDto.getLastName());
            user.setPhone(updateUserDto.getPhone());
            userRepository.save(user);
            return updateUserMapping.mapToUpdateUserDto(user);
        }
        return null;
    }

    /**
     * Метод обновления аватара авторизованного пользователя
     *
     * @param avatar
     * @param authentication
     * @throws IOException
     */
    @Override
    public void setAvatar(MultipartFile avatar, Authentication authentication) throws IOException {
        User user = userRepository.findByUserName(authentication.getName());
        user.setImage(workingFile.getFilePath(avatarImagesDir, avatar).toString());
        user.setFileSize(avatar.getSize());
        user.setMediaType(avatar.getContentType());
        user.setData(avatar.getBytes());
        userRepository.save(user);
    }

    /**
     * Метод получения аватара пользователя
     *
     * @param id
     * @return byte[]
     * @throws IOException
     */
    @Override
    public byte[] getImage(Integer id) throws IOException {
        return userRepository.findById(id).get().getData();
    }

    /**
     * Метод поиска пользователя
     *
     * @param userName
     * @return User
     */
    @Override
    public User getUser(String userName) {
        return userRepository.findByUserName(userName);
    }
}
