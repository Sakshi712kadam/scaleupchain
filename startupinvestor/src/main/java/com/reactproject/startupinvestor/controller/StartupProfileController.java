package com.reactproject.startupinvestor.controller;

import com.reactproject.startupinvestor.dto.StartupProfileDto;
import com.reactproject.startupinvestor.entities.StartupProfile;
import com.reactproject.startupinvestor.service.StartupProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/startup-profile")
public class StartupProfileController {

    @Autowired
    private StartupProfileService startupProfileService;

    @PostMapping
    public ResponseEntity<StartupProfile> createStartupProfile(@RequestBody StartupProfileDto startupProfileDTO) {
        StartupProfile savedProfile = startupProfileService.saveStartupProfile(startupProfileDTO);
        return ResponseEntity.ok(savedProfile);
    }

    @GetMapping
    public List<StartupProfile> getAllStartupProfiles() {
        List<StartupProfile> profiles = startupProfileService.getAllStartupProfiles();
        return profiles;
    }

    @GetMapping("/{id}")
    public ResponseEntity<StartupProfile> getStartupProfileById(@PathVariable Long id) {
        Optional<StartupProfile> profile = startupProfileService.getStartupProfileById(id);
        if (profile.isPresent()) {
            return ResponseEntity.ok(profile.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<StartupProfile> updateStartupProfile(
            @PathVariable Long id,
            @RequestBody StartupProfileDto startupProfileDTO) {
        Optional<StartupProfile> updatedProfile = startupProfileService.updateStartupProfile(id, startupProfileDTO);
        if (updatedProfile.isPresent()) {
            return ResponseEntity.ok(updatedProfile.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteStartupProfile(@PathVariable Long id) {
        boolean isDeleted = startupProfileService.deleteStartupProfile(id);
        if (isDeleted) {
            return ResponseEntity.noContent().build(); // 204 No Content
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
