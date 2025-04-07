package com.pet_portal.application.service;

import com.pet_portal.application.entity.Pet;
import com.pet_portal.application.repository.PetRepository;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PetService {
    private final PetRepository petRepository;

    public PetService(PetRepository petRepository) {
        this.petRepository = petRepository;
    }

    // Method to create a pet
    public Pet createPet(Pet pet) {
        return petRepository.save(pet);
    }

    // Method to get all pets, optionally filtered by adoption status
    public List<Pet> getAllPets(String status) {
        if (status != null) {
            return petRepository.findByAdoptionStatus(status);
        }
        return petRepository.findAll();
    }

    // Method to get a pet by ID
    public Optional<Pet> getPetById(Long petId) {
        return petRepository.findById(petId);
    }

    // Method to get paginated pets
    public Page<Pet> getPaginatedPets(int page, int size, String sortBy, String sortOrder) {
        Sort sort = sortOrder.equalsIgnoreCase("desc") ? Sort.by(sortBy).descending() : Sort.by(sortBy).ascending();
        Pageable pageable = PageRequest.of(page, size, sort);
        return petRepository.findAll(pageable);
    }

    // Method to update a pet's details
    public Optional<Pet> updatePet(Long petId, Pet petDetails) {
        return petRepository.findById(petId).map(existingPet -> {
            existingPet.setName(petDetails.getName());
            existingPet.setSpecies(petDetails.getSpecies());
            existingPet.setBreed(petDetails.getBreed());
            existingPet.setAge(petDetails.getAge());
            existingPet.setHealthStatus(petDetails.getHealthStatus());
            existingPet.setTemperament(petDetails.getTemperament());
            existingPet.setAdoptionStatus(petDetails.getAdoptionStatus());
            return petRepository.save(existingPet);
        });
    }
}
