package com.photo.photo_archive.repository;

import com.photo.photo_archive.model.PhotoWork;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PhotoWorkRepository extends JpaRepository<PhotoWork, Integer> {
    List<PhotoWork> findByPhotographerId(Integer photographerId);
    List<PhotoWork> findByGenreId(Integer genreId);
    List<PhotoWork> findByStatus(String status);
    List<PhotoWork> findByExhibitionsId(Integer exhibitionId);
}