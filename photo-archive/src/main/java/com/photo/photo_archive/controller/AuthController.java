package com.photo.photo_archive.controller;

import com.photo.photo_archive.model.AppUser;
import com.photo.photo_archive.model.Role;
import com.photo.photo_archive.repository.AppUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;
import org.springframework.validation.BindingResult;


@Controller
@RequiredArgsConstructor
public class AuthController {

    private final AppUserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        model.addAttribute("user", new AppUser());
        return "register";
    }

    @PostMapping("/register")
    public String processRegistration(@Valid @ModelAttribute("user") AppUser user,
                                      BindingResult bindingResult,
                                      Model model) {

        // Проверка валидации аннотаций
        if (bindingResult.hasErrors()) {
            return "register";
        }

        // Проверка уникальности email
        if (userRepository.findByEmail(user.getEmail()).isPresent()) {
            model.addAttribute("emailError", "Email уже используется");
            return "register";
        }

        try {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            user.setRole(Role.ROLE_USER);
            userRepository.save(user);
        } catch (Exception e) {
            model.addAttribute("registrationError", "Ошибка при регистрации");
            return "register";
        }

        return "redirect:/login?registered";
    }
}