package com.photo.photo_archive.controller;

import com.photo.photo_archive.model.AppUser;
import com.photo.photo_archive.repository.AppUserRepository;
import lombok.RequiredArgsConstructor;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
public class ProfileController {

    private final AppUserRepository userRepository;

    @GetMapping("/profile")
    public String profile(@AuthenticationPrincipal UserDetails userDetails, Model model) {
        String email = userDetails.getUsername();

        AppUser user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Пользователь не найден: " + email));

        model.addAttribute("user", user);

        return "profile";
    }

    @PostMapping("/profile/update")
    public String updateProfile(@AuthenticationPrincipal UserDetails userDetails,
                                @RequestParam String firstName,
                                @RequestParam String lastName) {
        String email = userDetails.getUsername();
        AppUser user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Пользователь не найден"));

        user.setFirstName(firstName);
        user.setLastName(lastName);
        userRepository.save(user);

        return "redirect:/profile";
    }
}