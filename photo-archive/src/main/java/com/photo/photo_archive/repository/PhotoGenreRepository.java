package com.photo.photo_archive.repository;

import com.photo.photo_archive.model.PhotoGenre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PhotoGenreRepository extends JpaRepository<PhotoGenre, Integer> {
    PhotoGenre findByName(String name);
    boolean existsByName(String name);

}
