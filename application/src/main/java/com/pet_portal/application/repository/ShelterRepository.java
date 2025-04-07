package com.pet_portal.application.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pet_portal.application.entity.Shelter;

public interface ShelterRepository extends JpaRepository<Shelter, Long> {
    Optional<Shelter> findByName(String name);

}
