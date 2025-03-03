package com.reactproject.startupinvestor.service;

import com.reactproject.startupinvestor.dto.InvestorProfileDto;
import com.reactproject.startupinvestor.entities.InvestorProfile;
import com.reactproject.startupinvestor.repositories.InvestorProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class InvestorProfileService {

    @Autowired
    private InvestorProfileRepository investorProfileRepository;

    public InvestorProfile saveInvestorProfile(InvestorProfileDto investorProfileDTO) {
        InvestorProfile investorProfile = new InvestorProfile();
                investorProfile.setInvestorName(investorProfileDTO.getInvestorName());
                investorProfile.setMinInvestmentAmount(investorProfileDTO.getMinInvestmentAmount());
                investorProfile.setPreferredSector(investorProfileDTO.getPreferredSector());

        return investorProfileRepository.save(investorProfile);
    }

    public List<InvestorProfile> getAllInvestorProfiles() {
        return investorProfileRepository.findAll();
    }

    public Optional<InvestorProfile> getInvestorProfileById(Long id) {
        return investorProfileRepository.findById(id);
    }

    public InvestorProfile updateInvestorProfile(Long id, InvestorProfileDto investorProfileDTO) {
        Optional<InvestorProfile> existingProfile = investorProfileRepository.findById(id);

        if (existingProfile.isPresent()) {
            InvestorProfile investorProfile = existingProfile.get();
            investorProfile.setInvestorName(investorProfileDTO.getInvestorName());
            investorProfile.setMinInvestmentAmount(investorProfileDTO.getMinInvestmentAmount());
            investorProfile.setPreferredSector(investorProfileDTO.getPreferredSector());

            return investorProfileRepository.save(investorProfile);
        }
        return null;
    }

    public void deleteInvestorProfile(Long id) {
        investorProfileRepository.deleteById(id);
    }
}