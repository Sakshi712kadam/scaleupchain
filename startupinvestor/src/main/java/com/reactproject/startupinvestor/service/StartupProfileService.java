package com.reactproject.startupinvestor.service;

import com.reactproject.startupinvestor.dto.StartupProfileDto;
import com.reactproject.startupinvestor.entities.StartupProfile;
import com.reactproject.startupinvestor.repositories.StartupProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StartupProfileService {

    @Autowired
    private StartupProfileRepository startupProfileRepository;


    public StartupProfile saveStartupProfile(StartupProfileDto startupProfileDto) {
        StartupProfile startupProfile = convertDTOToEntity(startupProfileDto);
        return startupProfileRepository.save(startupProfile);
    }

    public List<StartupProfile> getAllStartupProfiles() {
        return startupProfileRepository.findAll();
    }

    public Optional<StartupProfile> getStartupProfileById(Long id) {
        return startupProfileRepository.findById(id);
    }

    public Optional<StartupProfile> updateStartupProfile(Long id, StartupProfileDto startupProfileDTO) {
        Optional<StartupProfile> existingProfile = startupProfileRepository.findById(id);
        if (existingProfile.isPresent()) {
            StartupProfile profile = existingProfile.get();

            profile.setName(startupProfileDTO.getName());
            profile.setFounderName(startupProfileDTO.getFounderName());
            profile.setSector(startupProfileDTO.getSector());
            profile.setFundingRequirements(startupProfileDTO.getFundingRequirements());
            profile.setStage(startupProfileDTO.getStage());
            profile.setDescription(startupProfileDTO.getDescription());
            profile.setVisionMission(startupProfileDTO.getVisionMission());
            profile.setTeamSize(startupProfileDTO.getTeamSize());
            profile.setFoundedDate(startupProfileDTO.getFoundedDate());

            return Optional.of(startupProfileRepository.save(profile));
        } else {
            return Optional.empty();
        }
    }


    public boolean deleteStartupProfile(Long id) {
        Optional<StartupProfile> existingProfile = startupProfileRepository.findById(id);
        if (existingProfile.isPresent()) {
            startupProfileRepository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }


    private StartupProfile convertDTOToEntity(StartupProfileDto startupProfileDTO) {
        StartupProfile startupProfile = new StartupProfile();
        startupProfile.setName(startupProfileDTO.getName());
        startupProfile.setFounderName(startupProfileDTO.getFounderName());
        startupProfile.setSector(startupProfileDTO.getSector());
        startupProfile.setFundingRequirements(startupProfileDTO.getFundingRequirements());
        startupProfile.setStage(startupProfileDTO.getStage());
        startupProfile.setDescription(startupProfileDTO.getDescription());
        startupProfile.setVisionMission(startupProfileDTO.getVisionMission());
        startupProfile.setTeamSize(startupProfileDTO.getTeamSize());
        startupProfile.setFoundedDate(startupProfileDTO.getFoundedDate());
        return startupProfile;
    }
}
