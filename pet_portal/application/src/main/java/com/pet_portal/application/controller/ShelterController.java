package com.pet_portal.application.controller;

import com.pet_portal.application.entity.Shelter;
import com.pet_portal.application.service.ShelterService;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/shelters")
public class ShelterController {
    private final ShelterService shelterService;

    public ShelterController(ShelterService shelterService) {
        this.shelterService = shelterService;
    }

    @PostMapping
    public ResponseEntity<Shelter> createShelter(@RequestBody Shelter shelter) {
        return ResponseEntity.status(201).body(shelterService.createShelter(shelter));
    }

    @GetMapping
    public ResponseEntity<List<Shelter>> getAllShelters() {
        return ResponseEntity.ok(shelterService.getAllShelters());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Shelter> getShelterById(@PathVariable Long id) {
        Optional<Shelter> shelter = shelterService.getShelterById(id);
        return shelter.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Shelter> updateShelter(@PathVariable Long id, @RequestBody Shelter shelter) {
        Optional<Shelter> updatedShelter = shelterService.updateShelter(id, shelter);
        return updatedShelter.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteShelter(@PathVariable Long id) {
        if (shelterService.deleteShelter(id)) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/paginate")
    public ResponseEntity<Page<Shelter>> getPaginatedShelters(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        return ResponseEntity.ok(shelterService.getPaginatedShelters(page, size));
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<Shelter> getShelterByName(@PathVariable String name) {
        Optional<Shelter> shelter = shelterService.getShelterByName(name);
        return shelter.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }
}
