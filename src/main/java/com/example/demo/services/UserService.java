package com.example.demo.services;

import com.example.demo.dao.UserRepository;
import com.example.demo.entities.ConfirmationToken;
import com.example.demo.entities.User;
import com.example.demo.enums.Role;
import com.example.demo.exceptions.EmailExists;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.UUID;


@Service
@AllArgsConstructor
public class UserService implements UserDetailsService {
    @Autowired
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final ConfirmationTokenService confirmationTokenService;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(email);
        if (user != null)
            return user;
        else
            throw new UsernameNotFoundException(String.format("user with email %s not found", email));
    }

    @Transactional
    @SneakyThrows
    public String signUpUser(User user) {
        boolean userExist = userRepository.findByEmail(user.getEmail()) != null;

        if (userExist) {
            User checkIsEnable = userRepository.findByEmail(user.getEmail());
            if (!checkIsEnable.isEnabled()) {
                saveToken(user);
            }
            throw new EmailExists();
        }

        String encodePassword = bCryptPasswordEncoder.encode(user.getPassword());
        user.setRole(Role.USER);
        user.setPassword(encodePassword);
        return saveToken(user);
    }

    public String saveToken(User user) {

        String token = UUID.randomUUID().toString();
        ConfirmationToken confirmationToken = new ConfirmationToken(
                token,
                LocalDateTime.now(),
                LocalDateTime.now().plusMinutes(15),
                user
        );

        confirmationTokenService.saveConfirmationToken(confirmationToken);

        return token;
    }

    public void enableUser(String email) {
        userRepository.enableUser(email);
    }
}
