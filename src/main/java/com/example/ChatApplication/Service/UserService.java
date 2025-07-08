package com.example.ChatApplication.Service;

import com.example.ChatApplication.Model.User;
import com.example.ChatApplication.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public Optional<User> register(User user) {
        if (userRepository.findByEmail(user.getEmail()).isPresent()) {
            return Optional.empty();
        }

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return Optional.of(userRepository.save(user));
    }

    public Optional<User> login(String email, String rawPassword) {
        return userRepository.findByEmail(email)
                .filter(user -> passwordEncoder.matches(rawPassword, user.getPassword()));
    }

    public Optional<User> getById(String userId) {
        return userRepository.findById(userId);
    }

    public void save(User user) {
        userRepository.save(user);
    }
}
