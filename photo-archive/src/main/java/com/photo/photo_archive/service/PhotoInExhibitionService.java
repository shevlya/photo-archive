package com.photo.photo_archive.service;

import com.photo.photo_archive.model.Exhibition;
import com.photo.photo_archive.model.PhotoWork;
import com.photo.photo_archive.repository.ExhibitionRepository;
import com.photo.photo_archive.repository.PhotoWorkRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
@Transactional
public class PhotoInExhibitionService {
    private final PhotoWorkRepository photoWorkRepository;
    private final ExhibitionRepository exhibitionRepository;

    public PhotoInExhibitionService(PhotoWorkRepository photoWorkRepository,
                                    ExhibitionRepository exhibitionRepository) {
        this.photoWorkRepository = photoWorkRepository;
        this.exhibitionRepository = exhibitionRepository;
    }

    public void addPhotoToExhibition(Integer photoId, Integer exhibitionId) {
        PhotoWork photoWork = photoWorkRepository.findById(photoId)
                .orElseThrow(() -> new RuntimeException("PhotoWork not found"));
        Exhibition exhibition = exhibitionRepository.findById(exhibitionId)
                .orElseThrow(() -> new RuntimeException("Exhibition not found"));

        photoWork.getExhibitions().add(exhibition);
        photoWorkRepository.save(photoWork);
    }

    public void removePhotoFromExhibition(Integer photoId, Integer exhibitionId) {
        PhotoWork photoWork = photoWorkRepository.findById(photoId)
                .orElseThrow(() -> new RuntimeException("PhotoWork not found"));

        photoWork.getExhibitions().removeIf(e -> e.getId().equals(exhibitionId));
        photoWorkRepository.save(photoWork);
    }

    public List<PhotoWork> getPhotosInExhibition(Integer exhibitionId) {
        return photoWorkRepository.findByExhibitionsId(exhibitionId);
    }
}