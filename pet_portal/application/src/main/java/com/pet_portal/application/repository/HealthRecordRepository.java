package com.pet_portal.application.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pet_portal.application.entity.HealthRecord;

public interface HealthRecordRepository extends JpaRepository<HealthRecord, Long> {
    List<HealthRecord> findByPetId(Long petId);
}
