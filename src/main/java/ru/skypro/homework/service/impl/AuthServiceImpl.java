package ru.skypro.homework.service.impl;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.stereotype.Service;
import ru.skypro.homework.dto.RegisterDto;
import ru.skypro.homework.dto.mapper.RegisterMapping;
import ru.skypro.homework.model.User;
import ru.skypro.homework.repository.UserRepository;
import ru.skypro.homework.service.AuthService;

@Service
public class AuthServiceImpl implements AuthService {

    private final UserDetailsManager manager;
    private final PasswordEncoder encoder;
    private final RegisterMapping registerMapping;
    private final UserRepository userRepository;

    public AuthServiceImpl(UserDetailsManager manager,
                           PasswordEncoder passwordEncoder, RegisterMapping registerMapping, UserRepository userRepository) {
        this.manager = manager;
        this.encoder = passwordEncoder;
        this.registerMapping = registerMapping;
        this.userRepository = userRepository;
    }

    /**
     * Метод аунтификации пользователя
     *
     * @param username
     * @param password
     * @return boolean
     */
    @Override
    public boolean login(String username, String password) {
        if (!manager.userExists(username)) {
            return false;
        }
        UserDetails userDetails = manager.loadUserByUsername(username);
        return encoder.matches(password, userDetails.getPassword());
    }

    /**
     * Метод регистрации пользователя
     *
     * @param registerDto
     * @return boolean
     */
    @Override
    public boolean register(RegisterDto registerDto) {
        User user = registerMapping.mapToUser(registerDto);
        if (userRepository.findByEmail(user.getUserName()).isEmpty()) {
            user.setPassword(encoder.encode(user.getPassword()));
            user.setEmail(user.getUserName());
            userRepository.save(user);
            return true;
        }
        return false;
    }
}
