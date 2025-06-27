package com.photo.photo_archive.controller;

import com.photo.photo_archive.model.Photographer;
import com.photo.photo_archive.repository.PhotographerRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/photographers")
public class PhotographerViewController {

    private final PhotographerRepository photographerRepository;

    public PhotographerViewController(PhotographerRepository photographerRepository) {
        this.photographerRepository = photographerRepository;
    }

    @GetMapping
    public String listPhotographers(Model model) {
        model.addAttribute("photographers", photographerRepository.findAll());
        return "photographers"; // => templates/photographers.html
    }

    @GetMapping("/new")
    public String showCreateForm(Model model) {
        model.addAttribute("photographer", new Photographer());
        return "create-photographer"; // => templates/create-photographer.html
    }

    @PostMapping
    public String createPhotographer(@ModelAttribute Photographer photographer) {
        photographerRepository.save(photographer);
        return "redirect:/photographers";
    }

    @GetMapping("/{id}")
    public String showPhotographerProfile(@PathVariable Integer id, Model model) {
        return photographerRepository.findById(id)
                .map(photographer -> {
                    model.addAttribute("photographer", photographer);
                    return "photographer-profile"; // => templates/photographer-profile.html
                })
                .orElse("redirect:/photographers"); // если не найден, редирект на список
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Integer id, Model model) {
        return photographerRepository.findById(id)
                .map(photographer -> {
                    model.addAttribute("photographer", photographer);
                    return "edit-photographer"; // Шаблон с формой редактирования
                })
                .orElse("redirect:/photographers");
    }

    @PostMapping("/edit/{id}")
    public String updatePhotographer(@PathVariable Integer id, @ModelAttribute Photographer photographer) {
        photographer.setId(id);
        photographerRepository.save(photographer);
        return "redirect:/photographers/" + id;
    }

    @PostMapping("/delete/{id}")
    public String deletePhotographer(@PathVariable Integer id) {
        photographerRepository.deleteById(id);
        return "redirect:/photographers";
    }
}