package com.photo.photo_archive.repository;

import com.photo.photo_archive.model.PhotographerChangeRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface PhotographerChangeRequestRepository extends JpaRepository<PhotographerChangeRequest, Integer> {
    List<PhotographerChangeRequest> findByPhotographerId(Integer photographerId);
}