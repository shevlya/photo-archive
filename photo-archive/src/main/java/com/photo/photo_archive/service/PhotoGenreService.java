package com.photo.photo_archive.service;

import com.photo.photo_archive.model.PhotoGenre;
import com.photo.photo_archive.repository.PhotoGenreRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class PhotoGenreService {
    private final PhotoGenreRepository repository;

    public PhotoGenreService(PhotoGenreRepository repository) {
        this.repository = repository;
    }

    public List<PhotoGenre> findAll() {
        return repository.findAll();
    }

    public PhotoGenre findById(Integer id) {
        return repository.findById(id).orElse(null);
    }

    public PhotoGenre save(PhotoGenre genre) {
        return repository.save(genre);
    }

    public void deleteById(Integer id) {
        repository.deleteById(id);
    }

    public PhotoGenre findByName(String name) {
        return repository.findByName(name);
    }
}