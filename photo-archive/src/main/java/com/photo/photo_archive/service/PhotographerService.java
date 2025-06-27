package com.photo.photo_archive.service;

import com.photo.photo_archive.model.Photographer;
import com.photo.photo_archive.repository.PhotographerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PhotographerService {

    private final PhotographerRepository photographerRepository;

    public List<Photographer> findAll() {
        return photographerRepository.findAll();
    }

    public Optional<Photographer> findById(Integer id) {
        return photographerRepository.findById(id);
    }

    public Photographer save(Photographer photographer) {
        return photographerRepository.save(photographer);
    }

    public void deleteById(Integer id) {
        photographerRepository.deleteById(id);
    }
}