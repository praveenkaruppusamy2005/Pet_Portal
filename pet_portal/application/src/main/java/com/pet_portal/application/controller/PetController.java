package com.pet_portal.application.controller;

import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.pet_portal.application.entity.Pet;
import com.pet_portal.application.service.PetService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/pets")
public class PetController {
    private final PetService petService;

    public PetController(PetService petService) {
        this.petService = petService;
    }

    // POST endpoint to create a new pet
    @PostMapping("/post")
    public ResponseEntity<Pet> createPet(@RequestBody Pet pet) {
        return ResponseEntity.status(201).body(petService.createPet(pet));
    }

    // GET endpoint to fetch all pets (with optional filtering by adoption status)
    @GetMapping
    public ResponseEntity<List<Pet>> getAllPets(@RequestParam(required = false) String status) {
        return ResponseEntity.ok(petService.getAllPets(status));
    }

    // GET endpoint to fetch a pet by its ID
    @GetMapping("/{petId}")
    public ResponseEntity<Pet> getPetById(@PathVariable Long petId) {
        Optional<Pet> pet = petService.getPetById(petId);
        return pet.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // GET endpoint to fetch paginated list of pets
    @GetMapping("/paginate")
    public ResponseEntity<Page<Pet>> getPaginatedPets(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "id") String sortBy,
            @RequestParam(defaultValue = "asc") String sortOrder) {
        return ResponseEntity.ok(petService.getPaginatedPets(page, size, sortBy, sortOrder));
    }

    // PUT endpoint to update an existing pet's details by its ID
    @PutMapping("/{petId}")
    public ResponseEntity<Pet> updatePet(@PathVariable Long petId, @RequestBody Pet pet) {
        Optional<Pet> updatedPet = petService.updatePet(petId, pet);
        return updatedPet.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }
}
