package com.photo.photo_archive.controller;

import com.photo.photo_archive.repository.AwardRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AwardViewController {

    private final AwardRepository awardRepository;

    public AwardViewController(AwardRepository awardRepository) {
        this.awardRepository = awardRepository;
    }

    @GetMapping("/awards")
    public String showAwards(Model model) {
        model.addAttribute("awards", awardRepository.findAll());
        return "awards"; // будет искать awards.html в templates
    }
}