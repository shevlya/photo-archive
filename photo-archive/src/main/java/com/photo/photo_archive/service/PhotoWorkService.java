package com.photo.photo_archive.service;

import com.photo.photo_archive.model.PhotoWork;
import com.photo.photo_archive.repository.PhotoWorkRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class PhotoWorkService {
    private final PhotoWorkRepository repository;

    public PhotoWorkService(PhotoWorkRepository repository) {
        this.repository = repository;
    }

    public List<PhotoWork> findAll() {
        return repository.findAll();
    }

    public PhotoWork findById(Integer id) {
        return repository.findById(id).orElse(null);
    }

    public PhotoWork save(PhotoWork photoWork) {
        return repository.save(photoWork);
    }

    public void deleteById(Integer id) {
        repository.deleteById(id);
    }

    public List<PhotoWork> findByPhotographerId(Integer photographerId) {
        return repository.findByPhotographerId(photographerId);
    }

    public List<PhotoWork> findByGenreId(Integer genreId) {
        return repository.findByGenreId(genreId);
    }

    public List<PhotoWork> findByStatus(String status) {
        return repository.findByStatus(status);
    }
}