package com.healthpath.controllers;

import com.healthpath.models.User;
import com.healthpath.services.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    // ---------------- LOGIN ----------------
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestParam String email, @RequestParam String password) {
        Optional<User> userOpt = authService.login(email, password);

        if (userOpt.isPresent()) {
            User user = userOpt.get();
            // You can add JWT token generation here if needed
            return ResponseEntity.ok(user);
        } else {
            return ResponseEntity.status(401).body("Invalid email or password");
        }
    }

    // ---------------- SIGNUP ----------------
    @PostMapping("/signup")
    public ResponseEntity<?> signup(@RequestParam String email,
                                    @RequestParam String password,
                                    @RequestParam String name) {
        try {
            User newUser = authService.signup(email, password, name);
            return ResponseEntity.ok(newUser);
        } catch (RuntimeException ex) {
            return ResponseEntity.badRequest().body(ex.getMessage());
        }
    }
}
