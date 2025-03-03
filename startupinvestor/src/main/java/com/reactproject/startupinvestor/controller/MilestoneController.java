package com.reactproject.startupinvestor.controller;

import com.reactproject.startupinvestor.dto.MilestoneDTO;
import com.reactproject.startupinvestor.service.MilestoneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/milestones")
public class MilestoneController {

    @Autowired
    private MilestoneService milestoneService;

    @PostMapping
    public ResponseEntity<MilestoneDTO> createMilestone(@RequestBody MilestoneDTO milestoneDTO) {
        MilestoneDTO savedMilestone = milestoneService.saveMilestone(milestoneDTO);
        return ResponseEntity.ok(savedMilestone);
    }

    @GetMapping
    public List<MilestoneDTO> getAllMilestones() {
        return milestoneService.getAllMilestones();
    }


    @PutMapping("/{id}")
    public ResponseEntity<MilestoneDTO> updateMilestone(@PathVariable Long id, @RequestBody MilestoneDTO milestoneDTO) {
        MilestoneDTO updatedMilestone = milestoneService.updateMilestone(id, milestoneDTO);
        return ResponseEntity.ok(updatedMilestone);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMilestone(@PathVariable Long id) {
        milestoneService.deleteMilestone(id);
        return ResponseEntity.noContent().build();
    }

}
