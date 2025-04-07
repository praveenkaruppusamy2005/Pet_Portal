package com.pet_portal.application.service;

import com.pet_portal.application.entity.HealthRecord;
import com.pet_portal.application.repository.HealthRecordRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class HealthRecordService {
    private final HealthRecordRepository healthRecordRepository;

    public HealthRecordService(HealthRecordRepository healthRecordRepository) {
        this.healthRecordRepository = healthRecordRepository;
    }

    public HealthRecord createHealthRecord(HealthRecord healthRecord) {
        return healthRecordRepository.save(healthRecord);
    }

    public List<HealthRecord> getHealthRecordsByPetId(Long petId) {
        return healthRecordRepository.findByPetId(petId);
    }

    public Optional<HealthRecord> getHealthRecordById(Long id) {
        return healthRecordRepository.findById(id);
    }

    public Optional<HealthRecord> updateHealthRecord(Long id, HealthRecord updatedHealthRecord) {
        if (healthRecordRepository.existsById(id)) {
            updatedHealthRecord.setId(id);
            return Optional.of(healthRecordRepository.save(updatedHealthRecord));
        }
        return Optional.empty();
    }

    public boolean deleteHealthRecord(Long id) {
        if (healthRecordRepository.existsById(id)) {
            healthRecordRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
