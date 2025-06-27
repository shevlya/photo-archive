package com.photo.photo_archive.controller;

import com.photo.photo_archive.model.PhotoGenre;
import com.photo.photo_archive.repository.PhotoGenreRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/genres")
public class PhotoGenreController {

    private final PhotoGenreRepository photoGenreRepository;

    public PhotoGenreController(PhotoGenreRepository photoGenreRepository) {
        this.photoGenreRepository = photoGenreRepository;
    }

    @GetMapping
    public List<PhotoGenre> getAllGenres() {
        return photoGenreRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<PhotoGenre> getGenreById(@PathVariable Integer id) {
        return photoGenreRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public PhotoGenre createGenre(@RequestBody PhotoGenre genre) {
        return photoGenreRepository.save(genre);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PhotoGenre> updateGenre(@PathVariable Integer id, @RequestBody PhotoGenre genreDetails) {
        return photoGenreRepository.findById(id)
                .map(genre -> {
                    genre.setName(genreDetails.getName());
                    PhotoGenre updated = photoGenreRepository.save(genre);
                    return ResponseEntity.ok(updated);
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteGenre(@PathVariable Integer id) {
        return photoGenreRepository.findById(id)
                .map(genre -> {
                    photoGenreRepository.delete(genre);
                    return ResponseEntity.noContent().<Void>build();
                })
                .orElse(ResponseEntity.notFound().build());
    }
}
