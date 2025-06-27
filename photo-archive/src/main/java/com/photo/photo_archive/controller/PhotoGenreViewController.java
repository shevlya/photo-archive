package com.photo.photo_archive.controller;

import com.photo.photo_archive.model.PhotoGenre;
import com.photo.photo_archive.repository.PhotoGenreRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/genres")
public class PhotoGenreViewController {

    private final PhotoGenreRepository photoGenreRepository;

    public PhotoGenreViewController(PhotoGenreRepository photoGenreRepository) {
        this.photoGenreRepository = photoGenreRepository;
    }

    @GetMapping
    public String listGenres(Model model) {
        model.addAttribute("genres", photoGenreRepository.findAll());
        return "genres"; // => templates/genres.html
    }

    @GetMapping("/new")
    public String showCreateForm(Model model) {
        model.addAttribute("genre", new PhotoGenre());
        return "create-genre"; // => templates/create-genre.html
    }

    @PostMapping
    public String createGenre(@ModelAttribute PhotoGenre genre, Model model) {
        boolean exists = photoGenreRepository.existsByName(genre.getName());
        if (exists) {
            model.addAttribute("genre", genre); // чтобы форма была заполнена
            model.addAttribute("errorMessage", "Жанр с таким названием уже существует!");
            return "create-genre"; // возвращаем форму с ошибкой
        }
        photoGenreRepository.save(genre);
        return "redirect:/genres";
    }


    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Integer id, Model model) {
        model.addAttribute("genre", photoGenreRepository.findById(id).orElseThrow());
        return "create-genre"; // Используем ту же форму
    }

    @PostMapping("/delete/{id}")
    public String deleteGenre(@PathVariable Integer id) {
        photoGenreRepository.deleteById(id);
        return "redirect:/genres";
    }
}