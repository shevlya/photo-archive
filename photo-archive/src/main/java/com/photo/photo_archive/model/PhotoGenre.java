package com.photo.photo_archive.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;
import java.util.List;

@Data
@Entity
@Table(name = "photo_genre")
public class PhotoGenre {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "genre_id")
    private Integer id;

    @Column(name = "genre_name", nullable = false, unique = true, length = 100)
    private String name;

    @OneToMany(mappedBy = "genre")
    @JsonManagedReference
    private List<PhotoWork> photoWorks;

}
