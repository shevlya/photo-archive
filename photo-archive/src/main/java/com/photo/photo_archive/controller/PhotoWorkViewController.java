package com.photo.photo_archive.controller;

import com.photo.photo_archive.model.PhotoWork;
import com.photo.photo_archive.model.PhotoGenre;
import com.photo.photo_archive.model.Photographer;
import com.photo.photo_archive.repository.PhotoWorkRepository;
import com.photo.photo_archive.repository.PhotoGenreRepository;
import com.photo.photo_archive.repository.PhotographerRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/photoworks")
public class PhotoWorkViewController {

    private final PhotoWorkRepository photoWorkRepository;
    private final PhotographerRepository photographerRepository;
    private final PhotoGenreRepository photoGenreRepository;

    public PhotoWorkViewController(PhotoWorkRepository photoWorkRepository,
                                   PhotographerRepository photographerRepository,
                                   PhotoGenreRepository photoGenreRepository) {
        this.photoWorkRepository = photoWorkRepository;
        this.photographerRepository = photographerRepository;
        this.photoGenreRepository = photoGenreRepository;
    }

    @GetMapping
    public String listPhotoWorks(Model model) {
        model.addAttribute("photoWorks", photoWorkRepository.findAll());
        return "photoworks";
    }

    @GetMapping("/new")
    public String showCreateForm(Model model) {
        model.addAttribute("photographers", photographerRepository.findAll());
        model.addAttribute("genres", photoGenreRepository.findAll());
        return "create-photowork";
    }

    @PostMapping
    public String createPhotoWork(
            @RequestParam String title,
            @RequestParam(required = false) String technique,
            @RequestParam(required = false) String description,
            @RequestParam(required = false) String filePath,
            @RequestParam(required = false) String status,
            @RequestParam(required = false) String creationDate,
            @RequestParam Integer photographerId,
            @RequestParam(required = false) Integer genreId
    ) {
        PhotoWork photoWork = new PhotoWork();
        photoWork.setTitle(title);
        photoWork.setTechnique(technique);
        photoWork.setDescription(description);
        photoWork.setFilePath(filePath);
        photoWork.setStatus(status);

        if (creationDate != null && !creationDate.isEmpty()) {
            photoWork.setCreationDate(java.time.LocalDate.parse(creationDate));
        }

        photoWork.setPhotographer(photographerRepository.findById(photographerId).orElse(null));
        if (genreId != null) {
            photoWork.setGenre(photoGenreRepository.findById(genreId).orElse(null));
        }

        photoWorkRepository.save(photoWork);
        return "redirect:/photoworks";
    }
}