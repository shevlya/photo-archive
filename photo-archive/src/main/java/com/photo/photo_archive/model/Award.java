package com.photo.photo_archive.model;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDate;

@Data
@Entity
@Table(name = "award")
public class Award {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "award_id")
    private Integer id;

    @Column(name = "title", nullable = false, length = 255)
    private String title;

    @Column(name = "category", length = 100)
    private String category;

    @Column(name = "description_award", columnDefinition = "TEXT")
    private String description;

    @Column(name = "awarded_at")
    private LocalDate awardedAt;
}