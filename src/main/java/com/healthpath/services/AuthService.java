package com.healthpath.services;

import com.healthpath.models.User;
import com.healthpath.models.UserContact;
import com.healthpath.repositories.UserContactRepository;
import com.healthpath.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserContactRepository userContactRepository;

    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    // ---------------- LOGIN ----------------
    public Optional<User> login(String emailInput, String passwordInput) {
        Optional<UserContact> contactOpt = userContactRepository.findByValue(emailInput);

        if (contactOpt.isPresent()) {
            User user = contactOpt.get().getUser();

            if (passwordEncoder.matches(passwordInput, user.getPassword_hash())) {
                return Optional.of(user); // Login success
            } else {
                return Optional.empty(); // Wrong password
            }
        } else {
            return Optional.empty(); // Email not found
        }
    }

    // ---------------- SIGNUP ----------------
    public User signup(String email, String password, String name) {
        // Check if email already exists
        Optional<UserContact> existingContact = userContactRepository.findByValue(email);
        if (existingContact.isPresent()) {
            throw new RuntimeException("Email is already registered");
        }

        // Create new user
        User newUser = new User();
        newUser.setFirst_name(name);
        newUser.setPassword_hash(passwordEncoder.encode(password));
        userRepository.save(newUser);

        // Create linked UserContact
        UserContact userContact = new UserContact();
        userContact.setUser(newUser);
        userContact.setContactType("EMAIL");
        userContact.setValue(email);
        userContact.setIsPrimary(true);
        userContact.setIsVerified(false); // can later implement email verification
        userContactRepository.save(userContact);

        return newUser;
    }
}
