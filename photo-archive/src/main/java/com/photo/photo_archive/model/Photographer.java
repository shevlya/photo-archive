package com.photo.photo_archive.model;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "photographer")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Photographer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "photographer_id")
    private Integer id;

    @Column(name = "first_name", nullable = false, length = 50)
    private String firstName;

    @Column(name = "last_name", nullable = false, length = 50)
    private String lastName;

    @Column(name = "birth_date")
    private LocalDate birthDate;

    @Column(name = "biography", columnDefinition = "TEXT")
    private String biography;

    @Column(name = "contact_info", length = 255)
    private String contactInfo;

    @Column(name = "specialization", length = 255)
    private String specialization;

    public void setId(Integer id) {
        this.id = id;
    }

    @OneToMany(mappedBy = "photographer", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<PhotographerChangeRequest> changeRequests;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "added_by_id")
    private AppUser addedBy;
}