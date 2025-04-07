package com.pet_portal.application.controller;

import com.pet_portal.application.entity.HealthRecord;
import com.pet_portal.application.service.HealthRecordService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/health-records")
public class HealthRecordController {
    private final HealthRecordService healthRecordService;

    public HealthRecordController(HealthRecordService healthRecordService) {
        this.healthRecordService = healthRecordService;
    }

    @PostMapping
    public ResponseEntity<HealthRecord> createHealthRecord(@RequestBody HealthRecord healthRecord) {
        return ResponseEntity.status(201).body(healthRecordService.createHealthRecord(healthRecord));
    }

    @GetMapping("/pet/{petId}")
    public ResponseEntity<List<HealthRecord>> getHealthRecordsByPetId(@PathVariable Long petId) {
        return ResponseEntity.ok(healthRecordService.getHealthRecordsByPetId(petId));
    }

    @GetMapping("/{id}")
    public ResponseEntity<HealthRecord> getHealthRecordById(@PathVariable Long id) {
        Optional<HealthRecord> healthRecord = healthRecordService.getHealthRecordById(id);
        return healthRecord.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<HealthRecord> updateHealthRecord(@PathVariable Long id,
            @RequestBody HealthRecord healthRecord) {
        Optional<HealthRecord> updatedRecord = healthRecordService.updateHealthRecord(id, healthRecord);
        return updatedRecord.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteHealthRecord(@PathVariable Long id) {
        if (healthRecordService.deleteHealthRecord(id)) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
