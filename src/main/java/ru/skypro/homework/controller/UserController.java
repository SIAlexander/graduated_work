package ru.skypro.homework.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.NewPassword;
import ru.skypro.homework.dto.UpdateUser;
import ru.skypro.homework.dto.User;
import ru.skypro.homework.service.UserService;

import java.io.IOException;

@Slf4j
@CrossOrigin(value = "http://localhost:3000")
@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
@Tag(name = "Пользователи")
public class UserController {

    private final UserService userService;

    @Operation(summary = "Обновление пароля")
    @PostMapping("/set_password")
    public ResponseEntity<Void> setPassword(@RequestBody NewPassword newPassword) {
        if (newPassword.getNewPassword().length() <= 8 || newPassword.getNewPassword().length() >= 16) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        } else {
            userService.setPassword(newPassword.getCurrentPassword(), newPassword.getNewPassword());
            return ResponseEntity.ok().build();
        }
    }

    @Operation(summary = "Получение информации об авторизованном пользователе")
    @GetMapping("/me")
    public ResponseEntity<User> getInformationUser() {
        return ResponseEntity.ok(userService.getInformationUser());
    }

    @Operation(summary = "Обновление информации об авторизованном пользователе")
    @PatchMapping("/me")
    public  ResponseEntity<UpdateUser> setInformationUser(@RequestBody UpdateUser updateUser){
        return ResponseEntity.ok(userService.setInformationUser(updateUser));
    }

    @Operation(summary = "Обновление аватара авторизованного пользователя")
    @PatchMapping(value = "/me/image", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> setAds(@RequestParam MultipartFile avatarFile) throws IOException {
        userService.setAvatar(avatarFile);
        return ResponseEntity.ok().build();
    }


}
