package com.healthpath.services;

import com.healthpath.dao.UserDao;
import com.healthpath.models.User;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private final UserDao userDao;
    // Add this method to expose PasswordEncoder
    @Getter
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImpl(UserDao userDao, PasswordEncoder passwordEncoder) {
        this.userDao = userDao;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public Optional<User> findByEmail(String email) {
        return userDao.findByEmail(email);
    }

    @Override
    @Transactional
    public void registerUser(User user) {
        System.out.println("Registering user: " + user.getEmail());
        if (user.getFirstName() == null || user.getFirstName().trim().isEmpty()) {
            throw new IllegalArgumentException("First name is required!");
        }
        if (userDao.findByEmail(user.getEmail()).isPresent()) {
            throw new IllegalStateException("Email already registered!");
        }
        user.setPasswordHash(passwordEncoder.encode(user.getPasswordHash()));
        user.setCreatedAt(LocalDateTime.now());
        Long userId = userDao.save(user);
        System.out.println("New user saved with ID: " + userId);
    }

    @Override
    public User getCurrentUser() {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        return findByEmail(email).orElseThrow(() -> new RuntimeException("User not found"));

    }
    @Override
    public PasswordEncoder getPasswordEncoder() {
        return passwordEncoder; // Return the injected PasswordEncoder
    }
}