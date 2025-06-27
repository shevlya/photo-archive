package com.photo.photo_archive.repository;

import com.photo.photo_archive.model.Exhibition;
import org.springframework.data.jpa.repository.JpaRepository;
import java.time.LocalDate;
import java.util.List;

public interface ExhibitionRepository extends JpaRepository<Exhibition, Integer> {
    List<Exhibition> findByStartDateLessThanEqualAndEndDateGreaterThanEqual(LocalDate date1, LocalDate date2);
    List<Exhibition> findByLocationContainingIgnoreCase(String location);
}