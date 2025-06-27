package com.photo.photo_archive.controller;

import com.photo.photo_archive.model.PhotoWork;
import com.photo.photo_archive.repository.PhotoWorkRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/photoworks")
public class PhotoWorkController {

    private final PhotoWorkRepository photoWorkRepository;

    public PhotoWorkController(PhotoWorkRepository photoWorkRepository) {
        this.photoWorkRepository = photoWorkRepository;
    }

    @GetMapping
    public List<PhotoWork> getAllPhotoWorks() {
        return photoWorkRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<PhotoWork> getPhotoWorkById(@PathVariable Integer id) {
        return photoWorkRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public PhotoWork createPhotoWork(@RequestBody PhotoWork photoWork) {
        return photoWorkRepository.save(photoWork);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PhotoWork> updatePhotoWork(@PathVariable Integer id, @RequestBody PhotoWork photoWorkDetails) {
        return photoWorkRepository.findById(id)
                .map(photoWork -> {
                    photoWork.setTitle(photoWorkDetails.getTitle());
                    photoWork.setCreationDate(photoWorkDetails.getCreationDate());
                    photoWork.setTechnique(photoWorkDetails.getTechnique());
                    photoWork.setDescription(photoWorkDetails.getDescription());
                    photoWork.setFilePath(photoWorkDetails.getFilePath());
                    photoWork.setStatus(photoWorkDetails.getStatus());
                    photoWork.setPhotographer(photoWorkDetails.getPhotographer());
                    photoWork.setGenre(photoWorkDetails.getGenre());
                    photoWork.setExhibitions(photoWorkDetails.getExhibitions());
                    PhotoWork updated = photoWorkRepository.save(photoWork);
                    return ResponseEntity.ok(updated);
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePhotoWork(@PathVariable Integer id) {
        return photoWorkRepository.findById(id)
                .map(photoWork -> {
                    photoWorkRepository.delete(photoWork);
                    return ResponseEntity.noContent().<Void>build();
                })
                .orElse(ResponseEntity.notFound().build());
    }
}
