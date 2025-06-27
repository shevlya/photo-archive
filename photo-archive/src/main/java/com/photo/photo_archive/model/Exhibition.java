package com.photo.photo_archive.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "exhibition")
public class Exhibition {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "exhibition_id")
    private Integer id;

    @Column(name = "title", nullable = false, length = 255)
    private String title;

    @Column(name = "start_date")
    private LocalDate startDate;

    @Column(name = "end_date")
    private LocalDate endDate;

    @Column(name = "location_exhibition", length = 255)
    private String location;

    @Column(name = "description_exhibition", columnDefinition = "TEXT")
    private String description;

    @ManyToMany(mappedBy = "exhibitions")
    private Set<PhotoWork> photoWorks = new HashSet<>();

    public Integer getId() {
        return this.id;
    }
}