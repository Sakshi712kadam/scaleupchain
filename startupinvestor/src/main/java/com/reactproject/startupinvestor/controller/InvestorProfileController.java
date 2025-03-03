package com.reactproject.startupinvestor.controller;

import com.reactproject.startupinvestor.dto.InvestorProfileDto;
import com.reactproject.startupinvestor.entities.InvestorProfile;
import com.reactproject.startupinvestor.service.InvestorProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/api/investor-profile")
public class InvestorProfileController {

    @Autowired
    private InvestorProfileService investorProfileService;


    @PostMapping
    public ResponseEntity<InvestorProfile> createInvestorProfile(@RequestBody InvestorProfileDto investorProfileDTO) {
        InvestorProfile savedProfile = investorProfileService.saveInvestorProfile(investorProfileDTO);
        return ResponseEntity.ok(savedProfile);
    }

    @GetMapping
    public ResponseEntity<List<InvestorProfile>> getAllInvestorProfiles() {
        List<InvestorProfile> profiles = investorProfileService.getAllInvestorProfiles();
        return ResponseEntity.ok(profiles);
    }

    @GetMapping("/{id}")
    public ResponseEntity<InvestorProfile> getInvestorProfileById(@PathVariable Long id) {
        Optional<InvestorProfile> profile = investorProfileService.getInvestorProfileById(id);
        return profile.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<InvestorProfile> updateInvestorProfile(@PathVariable Long id, @RequestBody InvestorProfileDto investorProfileDTO) {
        InvestorProfile updatedProfile = investorProfileService.updateInvestorProfile(id, investorProfileDTO);
        if (updatedProfile != null) {
            return ResponseEntity.ok(updatedProfile);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteInvestorProfile(@PathVariable Long id) {
        investorProfileService.deleteInvestorProfile(id);
        return ResponseEntity.noContent().build();
    }
}