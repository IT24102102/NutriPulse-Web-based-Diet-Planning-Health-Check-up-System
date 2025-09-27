package com.healthpath.controller;

import com.healthpath.models.User;
import com.healthpath.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/signup")
    public String showSignupForm(Model model) {
        model.addAttribute("user", new User());
        return "signup";
    }

    @PostMapping("/register")
    public String registerUser(@ModelAttribute User user, Model model) {
        try {
            userService.registerUser(user);
            return "redirect:/login";
        } catch (IllegalStateException e) {
            model.addAttribute("message", e.getMessage());
            return "signup";
        }
    }

    @GetMapping("/login")
    public String showLoginForm(Model model) {
        model.addAttribute("user", new User());
        return "login";
    }

    @PostMapping("/login")
    public String loginUser(@ModelAttribute User user, Model model) {
        try {
            User existingUser = userService.findByEmail(user.getEmail())
                    .orElseThrow(() -> new IllegalStateException("Email not found"));
            if (!userService.getPasswordEncoder().matches(user.getPasswordHash(), existingUser.getPasswordHash())) {
                throw new IllegalStateException("Invalid password");
            }
            // Simulate setting authenticated user
            model.addAttribute("currentUser", existingUser);
            return "redirect:/dashboard";
        } catch (IllegalStateException e) {
            model.addAttribute("message", e.getMessage());
            return "login";
        }
    }

    @GetMapping("/dashboard")
    public String showDashboard(Model model) {
        User currentUser = userService.getCurrentUser();
        model.addAttribute("currentUser", currentUser);
        return "dashboard";
    }
}