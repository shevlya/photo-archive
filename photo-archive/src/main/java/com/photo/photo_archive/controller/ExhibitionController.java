package com.photo.photo_archive.controller;

import com.photo.photo_archive.model.Exhibition;
import com.photo.photo_archive.repository.ExhibitionRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/exhibitions")
public class ExhibitionController {

    private final ExhibitionRepository exhibitionRepository;

    public ExhibitionController(ExhibitionRepository exhibitionRepository) {
        this.exhibitionRepository = exhibitionRepository;
    }

    @GetMapping
    public List<Exhibition> getAllExhibitions() {
        return exhibitionRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Exhibition> getExhibitionById(@PathVariable Integer id) {
        return exhibitionRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Exhibition createExhibition(@RequestBody Exhibition exhibition) {
        return exhibitionRepository.save(exhibition);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Exhibition> updateExhibition(@PathVariable Integer id, @RequestBody Exhibition exhibitionDetails) {
        return exhibitionRepository.findById(id)
                .map(exhibition -> {
                    exhibition.setTitle(exhibitionDetails.getTitle());
                    exhibition.setStartDate(exhibitionDetails.getStartDate());
                    exhibition.setEndDate(exhibitionDetails.getEndDate());
                    exhibition.setLocation(exhibitionDetails.getLocation());
                    exhibition.setDescription(exhibitionDetails.getDescription());
                    Exhibition updated = exhibitionRepository.save(exhibition);
                    return ResponseEntity.ok(updated);
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteExhibition(@PathVariable Integer id) {
        return exhibitionRepository.findById(id)
                .map(exhibition -> {
                    exhibitionRepository.delete(exhibition);
                    return ResponseEntity.noContent().<Void>build();
                })
                .orElse(ResponseEntity.notFound().build());
    }
}
