package com.photo.photo_archive.controller;

import com.photo.photo_archive.model.Exhibition;
import com.photo.photo_archive.model.PhotoWork;
import com.photo.photo_archive.repository.ExhibitionRepository;
import com.photo.photo_archive.repository.PhotoWorkRepository;
import com.photo.photo_archive.service.PhotoInExhibitionService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/exhibitions")
public class ExhibitionViewController {

    private final ExhibitionRepository exhibitionRepository;
    private final PhotoWorkRepository photoWorkRepository;
    private final PhotoInExhibitionService photoInExhibitionService;

    public ExhibitionViewController(ExhibitionRepository exhibitionRepository,
                                    PhotoWorkRepository photoWorkRepository,
                                    PhotoInExhibitionService photoInExhibitionService) {
        this.exhibitionRepository = exhibitionRepository;
        this.photoWorkRepository = photoWorkRepository;
        this.photoInExhibitionService = photoInExhibitionService;
    }

    @GetMapping
    public String listExhibitions(Model model) {
        model.addAttribute("exhibitions", exhibitionRepository.findAll());
        return "exhibitions";
    }

    @GetMapping("/{id}")
    public String exhibitionDetails(@PathVariable Integer id, Model model) {
        Exhibition exhibition = exhibitionRepository.findById(id).orElseThrow();
        List<PhotoWork> allPhotos = photoWorkRepository.findAll();
        List<PhotoWork> photosInExhibition = photoInExhibitionService.getPhotosInExhibition(id);

        model.addAttribute("exhibition", exhibition);
        model.addAttribute("allPhotos", allPhotos);
        model.addAttribute("photosInExhibition", photosInExhibition);

        return "exhibition-detail"; // Новый шаблон
    }

    @PostMapping("/{id}/add-photo")
    public String addPhotoToExhibition(@PathVariable Integer id, @RequestParam Integer photoId) {
        photoInExhibitionService.addPhotoToExhibition(photoId, id);
        return "redirect:/exhibitions/" + id;
    }

    @PostMapping("/{id}/remove-photo")
    public String removePhotoFromExhibition(@PathVariable Integer id, @RequestParam Integer photoId) {
        photoInExhibitionService.removePhotoFromExhibition(photoId, id);
        return "redirect:/exhibitions/" + id;
    }

    @GetMapping("/new")
    public String showCreateForm(Model model) {
        model.addAttribute("exhibition", new Exhibition());
        return "create-exhibition";
    }

    @PostMapping
    public String createExhibition(@ModelAttribute Exhibition exhibition) {
        exhibitionRepository.save(exhibition);
        return "redirect:/exhibitions";
    }
}