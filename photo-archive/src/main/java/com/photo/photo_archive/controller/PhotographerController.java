package com.photo.photo_archive.controller;

import com.photo.photo_archive.model.Photographer;
import com.photo.photo_archive.repository.PhotographerRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/photographers")
public class PhotographerController {

    private final PhotographerRepository photographerRepository;

    public PhotographerController(PhotographerRepository photographerRepository) {
        this.photographerRepository = photographerRepository;
    }

    @GetMapping
    public List<Photographer> getAllPhotographers() {
        return photographerRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Photographer> getPhotographerById(@PathVariable Integer id) {
        return photographerRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Photographer createPhotographer(@RequestBody Photographer photographer) {
        return photographerRepository.save(photographer);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Photographer> updatePhotographer(@PathVariable Integer id, @RequestBody Photographer photographerDetails) {
        return photographerRepository.findById(id)
                .map(photographer -> {
                    photographer.setFirstName(photographerDetails.getFirstName());
                    photographer.setLastName(photographerDetails.getLastName());
                    photographer.setBirthDate(photographerDetails.getBirthDate());
                    photographer.setBiography(photographerDetails.getBiography());
                    photographer.setContactInfo(photographerDetails.getContactInfo());
                    photographer.setSpecialization(photographerDetails.getSpecialization());
                    Photographer updated = photographerRepository.save(photographer);
                    return ResponseEntity.ok(updated);
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePhotographer(@PathVariable Integer id) {
        return photographerRepository.findById(id)
                .map(photographer -> {
                    photographerRepository.delete(photographer);
                    return ResponseEntity.noContent().<Void>build();
                })
                .orElse(ResponseEntity.notFound().build());
    }
}
