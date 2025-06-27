package com.photo.photo_archive.service;

import com.photo.photo_archive.model.PhotographerChangeRequest;
import com.photo.photo_archive.repository.PhotographerChangeRequestRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PhotographerChangeRequestService {

    private final PhotographerChangeRequestRepository requestRepository;

    public List<PhotographerChangeRequest> getAllRequests() {
        return requestRepository.findAll();
    }

    public List<PhotographerChangeRequest> getRequestsForPhotographer(Integer photographerId) {
        return requestRepository.findByPhotographerId(photographerId);
    }

    public PhotographerChangeRequest createRequest(PhotographerChangeRequest request) {
        return requestRepository.save(request);
    }

    @Transactional
    public void approveRequest(Integer requestId) {
        PhotographerChangeRequest request = requestRepository.findById(requestId)
                .orElseThrow(() -> new IllegalArgumentException("Запрос не найден"));

        if (request.getApproved()) return;

        var photographer = request.getPhotographer();
        if (request.getSuggestedBiography() != null) {
            photographer.setBiography(request.getSuggestedBiography());
        }
        if (request.getSuggestedContactInfo() != null) {
            photographer.setContactInfo(request.getSuggestedContactInfo());
        }
        if (request.getSuggestedSpecialization() != null) {
            photographer.setSpecialization(request.getSuggestedSpecialization());
        }

        request.setApproved(true);
        requestRepository.save(request);
    }

    public void deleteRequest(Integer requestId) {
        requestRepository.deleteById(requestId);
    }
}