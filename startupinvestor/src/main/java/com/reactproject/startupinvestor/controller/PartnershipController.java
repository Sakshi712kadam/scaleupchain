package com.reactproject.startupinvestor.controller;

import com.reactproject.startupinvestor.dto.PartnershipDTO;
import com.reactproject.startupinvestor.entities.Partnership;
import com.reactproject.startupinvestor.service.PartnershipService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/partnerships")
public class PartnershipController {
    @Autowired
    private PartnershipService partnershipService;

    @PostMapping
    public ResponseEntity<Partnership> createPartnership(@RequestBody PartnershipDTO partnershipDTO) {
        Partnership partnership = new Partnership();
        partnership.setName(partnershipDTO.getName());
        partnership.setPartnerType(partnershipDTO.getPartnerType());
        partnership.setStartDate(partnershipDTO.getStartDate());
        return ResponseEntity.ok(partnershipService.createPartnership(partnership));
    }

    @GetMapping
    public ResponseEntity<List<Partnership>> getAllPartnerships() {
        return ResponseEntity.ok(partnershipService.getAllPartnerships());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Partnership> getPartnershipById(@PathVariable Long id) {
        return partnershipService.getPartnershipById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Partnership> updatePartnership(
            @PathVariable Long id,
            @RequestBody PartnershipDTO partnershipDTO) {
        Partnership updatedPartnership = new Partnership();
        updatedPartnership.setName(partnershipDTO.getName());
        updatedPartnership.setPartnerType(partnershipDTO.getPartnerType());
        updatedPartnership.setStartDate(partnershipDTO.getStartDate());
        return ResponseEntity.ok(partnershipService.updatePartnership(id, updatedPartnership));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePartnership(@PathVariable Long id) {
        partnershipService.deletePartnership(id);
        return ResponseEntity.noContent().build();
    }
}
