package ru.skypro.homework.config;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.stereotype.Component;
import ru.skypro.homework.dto.Role;
import ru.skypro.homework.model.User;
import ru.skypro.homework.repository.UserRepository;

@Component
public class MyUserDetailManager implements UserDetailsManager {

    private final UserRepository userRepository;

    public MyUserDetailManager(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void createUser(UserDetails user) {
        User myUser = new User();
        myUser.setEmail(user.getUsername());
        myUser.setUserName(user.getUsername());
        myUser.setPassword(user.getPassword());
        myUser.setRole(Role.valueOf(user.getAuthorities().iterator().next().getAuthority().substring(5)));
        userRepository.save(myUser);
    }

    @Override
    public void updateUser(UserDetails user) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void deleteUser(String username) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void changePassword(String oldPassword, String newPassword) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean userExists(String username) {
        return userRepository.existsByEmail(username);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByEmail(username)
                .map(MyUserDetails::new)
                .orElseThrow();
    }
}
