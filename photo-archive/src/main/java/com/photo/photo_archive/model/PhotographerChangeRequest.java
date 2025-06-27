package com.photo.photo_archive.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "photographer_change_request")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PhotographerChangeRequest {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "photographer_id", nullable = false)
    private Photographer photographer;

    @ManyToOne
    @JoinColumn(name = "requester_id", nullable = false)
    private AppUser requester;

    private String suggestedBiography;
    private String suggestedContactInfo;
    private String suggestedSpecialization;

    private Boolean approved = false;

    private LocalDateTime createdAt = LocalDateTime.now();
}

