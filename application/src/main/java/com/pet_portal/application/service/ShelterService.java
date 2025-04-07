package com.pet_portal.application.service;

import com.pet_portal.application.entity.Shelter;
import com.pet_portal.application.repository.ShelterRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ShelterService {
    private final ShelterRepository shelterRepository;

    public ShelterService(ShelterRepository shelterRepository) {
        this.shelterRepository = shelterRepository;
    }

    public Shelter createShelter(Shelter shelter) {
        return shelterRepository.save(shelter);
    }

    public List<Shelter> getAllShelters() {
        return shelterRepository.findAll();
    }

    public Optional<Shelter> getShelterById(Long id) {
        return shelterRepository.findById(id);
    }

    public Optional<Shelter> getShelterByName(String name) {
        return shelterRepository.findByName(name);
    }

    public Optional<Shelter> updateShelter(Long id, Shelter updatedShelter) {
        if (shelterRepository.existsById(id)) {
            updatedShelter.setId(id);
            return Optional.of(shelterRepository.save(updatedShelter));
        }
        return Optional.empty();
    }

    public boolean deleteShelter(Long id) {
        if (shelterRepository.existsById(id)) {
            shelterRepository.deleteById(id);
            return true;
        }
        return false;
    }

    public Page<Shelter> getPaginatedShelters(int page, int size) {
        return shelterRepository.findAll(PageRequest.of(page, size));
    }
}
