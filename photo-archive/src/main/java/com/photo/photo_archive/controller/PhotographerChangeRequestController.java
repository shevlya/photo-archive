package com.photo.photo_archive.controller;

import com.photo.photo_archive.model.PhotographerChangeRequest;
import com.photo.photo_archive.repository.PhotographerChangeRequestRepository;
import com.photo.photo_archive.repository.PhotographerRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/change_requests")
@RequiredArgsConstructor
public class PhotographerChangeRequestController {

    private final PhotographerChangeRequestRepository requestRepository;
    private final PhotographerRepository photographerRepository;

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping
    public String listRequests(Model model) {
        model.addAttribute("requests", requestRepository.findAll());
        return "change_requests/list";
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping("/approve/{id}")
    @Transactional
    public String approveRequest(@PathVariable Integer id) {
        var request = requestRepository.findById(id).orElse(null);
        if (request != null && !request.getApproved()) {
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
            photographerRepository.save(photographer);
            requestRepository.save(request);
        }
        return "redirect:/change_requests";
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping("/delete/{id}")
    public String deleteRequest(@PathVariable Integer id) {
        requestRepository.deleteById(id);
        return "redirect:/change_requests";
    }
}