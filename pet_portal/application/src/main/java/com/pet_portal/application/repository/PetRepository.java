package com.pet_portal.application.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pet_portal.application.entity.Pet;

public interface PetRepository extends JpaRepository<Pet, Long> {
    List<Pet> findByAdoptionStatus(String adoptionStatus);
}
