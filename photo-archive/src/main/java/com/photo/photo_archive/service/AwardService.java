package com.photo.photo_archive.service;

import com.photo.photo_archive.model.Award;
import com.photo.photo_archive.repository.AwardRepository;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.List;

@Service
public class AwardService {
    private final AwardRepository repository;

    public AwardService(AwardRepository repository) {
        this.repository = repository;
    }

    public List<Award> findAll() {
        return repository.findAll();
    }

    public Award findById(Integer id) {
        return repository.findById(id).orElse(null);
    }

    public Award save(Award award) {
        return repository.save(award);
    }

    public void deleteById(Integer id) {
        repository.deleteById(id);
    }

    public List<Award> findByCategory(String category) {
        return repository.findByCategory(category);
    }

    public List<Award> findByYear(int year) {
        LocalDate startDate = LocalDate.of(year, 1, 1);
        LocalDate endDate = LocalDate.of(year, 12, 31);
        return repository.findByAwardedAtBetween(startDate, endDate);
    }
}