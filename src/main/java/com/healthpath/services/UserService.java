package com.healthpath.services;

import com.healthpath.models.User;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

public interface UserService {
    Optional<User> findByEmail(String email);
    void registerUser(User user);
    User getCurrentUser();    // New method to get logged-in user
    PasswordEncoder getPasswordEncoder();
}