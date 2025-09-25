package com.healthpath.services;

import com.healthpath.models.User;
import com.healthpath.models.UserContact;
import com.healthpath.repositories.UserRepository;
import com.healthpath.repositories.UserContactRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final UserContactRepository userContactRepository;
    private final BCryptPasswordEncoder passwordEncoder;


    public UserService(UserRepository userRepository, UserContactRepository userContactRepository) {
        this.userRepository = userRepository;
        this.userContactRepository = userContactRepository;
        this.passwordEncoder = new BCryptPasswordEncoder();
    }

    // ================== LOGIN ==================
    public Optional<User> loginByEmail(String email) {
        Optional<UserContact> contactOpt = userContactRepository.findByValue(email);
        return contactOpt.map(UserContact::getUser);
    }

    // ================== SIGNUP ==================
    @Transactional
    public User signupUser(String name, String email, String password) {
        Optional<UserContact> existing = userContactRepository.findByValue(email);
        if (existing.isPresent()) {
            throw new RuntimeException("Email already registered");
        }

        User user = new User();
        user.setFirst_name(name);
        user.setPassword_hash(passwordEncoder.encode(password));
        user = userRepository.save(user);

        UserContact userContact = new UserContact();
        userContact.setUser(user);
        userContact.setContactType("EMAIL");
        userContact.setValue(email);
        userContact.setIsPrimary(true); // Correct setter
        userContactRepository.save(userContact);

        return user;
    }
}