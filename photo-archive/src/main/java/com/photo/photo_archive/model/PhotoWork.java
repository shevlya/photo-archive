package com.photo.photo_archive.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;
import java.util.HashSet;
import com.fasterxml.jackson.annotation.JsonBackReference;


@Getter
@Setter
@Entity
@Table(name = "photo_work")
public class PhotoWork {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "photo_id")
    private Integer id;

    @Column(name = "title", nullable = false, length = 255)
    private String title;

    @Column(name = "creation_date")
    private LocalDate creationDate;

    @Column(name = "technique", length = 100)
    private String technique;

    @Column(name = "description_photo", columnDefinition = "TEXT")
    private String description;

    @Column(name = "file_path", length = 512)
    private String filePath;

    @Column(name = "status_photo", length = 50)
    private String status;

    @ManyToOne
    @JoinColumn(name = "photographer_id", nullable = false)
    private Photographer photographer;

    @ManyToOne
    @JoinColumn(name = "genre_id")
    @JsonBackReference
    private PhotoGenre genre;


    @ManyToMany
    @JoinTable(
            name = "photo_in_exhibition",
            joinColumns = @JoinColumn(name = "photo_id"),
            inverseJoinColumns = @JoinColumn(name = "exhibition_id")
    )

    private Set<Exhibition> exhibitions = new HashSet<>(); // Должно быть это поле

}