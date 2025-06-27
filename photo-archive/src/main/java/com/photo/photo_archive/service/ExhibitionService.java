package com.photo.photo_archive.service;

import com.photo.photo_archive.model.Exhibition;
import com.photo.photo_archive.repository.ExhibitionRepository;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.List;

@Service
public class ExhibitionService {
    private final ExhibitionRepository repository;

    public ExhibitionService(ExhibitionRepository repository) {
        this.repository = repository;
    }

    public List<Exhibition> findAll() {
        return repository.findAll();
    }

    public Exhibition findById(Integer id) {
        return repository.findById(id).orElse(null);
    }

    public Exhibition save(Exhibition exhibition) {
        return repository.save(exhibition);
    }

    public void deleteById(Integer id) {
        repository.deleteById(id);
    }

    public List<Exhibition> findCurrentExhibitions() {
        LocalDate today = LocalDate.now();
        return repository.findByStartDateLessThanEqualAndEndDateGreaterThanEqual(today, today);
    }

    public List<Exhibition> findByLocation(String location) {
        return repository.findByLocationContainingIgnoreCase(location);
    }
}