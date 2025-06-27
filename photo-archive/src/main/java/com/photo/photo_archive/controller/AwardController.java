package com.photo.photo_archive.controller;

import com.photo.photo_archive.model.Award;
import com.photo.photo_archive.repository.AwardRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/awards")
public class AwardController {

    private final AwardRepository awardRepository;

    public AwardController(AwardRepository awardRepository) {
        this.awardRepository = awardRepository;
    }

    @GetMapping
    public List<Award> getAllAwards() {
        return awardRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Award> getAwardById(@PathVariable Integer id) {
        return awardRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Award createAward(@RequestBody Award award) {
        return awardRepository.save(award);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Award> updateAward(@PathVariable Integer id, @RequestBody Award awardDetails) {
        return awardRepository.findById(id)
                .map(award -> {
                    award.setTitle(awardDetails.getTitle());
                    award.setCategory(awardDetails.getCategory());
                    award.setDescription(awardDetails.getDescription());
                    award.setAwardedAt(awardDetails.getAwardedAt());
                    Award updatedAward = awardRepository.save(award);
                    return ResponseEntity.ok(updatedAward);
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAward(@PathVariable Integer id) {
        return awardRepository.findById(id)
                .map(award -> {
                    awardRepository.delete(award);
                    return ResponseEntity.noContent().<Void>build();
                })
                .orElse(ResponseEntity.notFound().build());
    }
}
