package com.photo.photo_archive.repository;

import com.photo.photo_archive.model.Award;
import org.springframework.data.jpa.repository.JpaRepository;
import java.time.LocalDate;
import java.util.List;

public interface AwardRepository extends JpaRepository<Award, Integer> {
    List<Award> findByCategory(String category);
    List<Award> findByAwardedAtBetween(LocalDate startDate, LocalDate endDate);
}