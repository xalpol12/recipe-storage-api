package dev.xalpol12.recipestorageapi.service;

import dev.xalpol12.recipestorageapi.repository.UserRepository;
import dev.xalpol12.recipestorageapi.repository.entities.User;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder encoder;

    private void saveUser(User user) {
        user.setPassword(encoder.encode(user.getPassword()));
        user.setRole("ROLE_USER");
        userRepository.save(user);
    }

    public HttpStatus save(User user) {
        if (userRepository.existsByEmail(user.getEmail())) {
            return HttpStatus.BAD_REQUEST;
        } else {
            saveUser(user);
            return HttpStatus.OK;
        }
    }

    public void saveAll(List<User> users) {
        for (User user : users) {
            saveUser(user);
        }
    }


}

