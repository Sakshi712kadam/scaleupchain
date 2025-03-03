package com.reactproject.startupinvestor.service;

import com.reactproject.startupinvestor.dto.FundingDTO;
import com.reactproject.startupinvestor.entities.Funding;
import com.reactproject.startupinvestor.repositories.FundingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class FundingService {

    @Autowired
    private FundingRepository fundingRepository;

    // Convert Entity to DTO
    private FundingDTO convertToDTO(Funding funding) {
        FundingDTO dto = new FundingDTO();
        dto.setId(funding.getId());
        dto.setFundingSource(funding.getFundingSource());
        dto.setAmount(funding.getAmount());
        dto.setDateReceived(funding.getDateReceived());
        return dto;
    }

    // Convert DTO to Entity
    private Funding convertToEntity(FundingDTO dto) {
        Funding funding = new Funding();
        funding.setId(dto.getId());
        funding.setFundingSource(dto.getFundingSource());
        funding.setAmount(dto.getAmount());
        funding.setDateReceived(dto.getDateReceived());
        return funding;
    }

    // Create or Update Funding
    public FundingDTO saveFunding(FundingDTO fundingDTO) {
        Funding funding = convertToEntity(fundingDTO);
        Funding savedFunding = fundingRepository.save(funding);
        return convertToDTO(savedFunding);
    }

    // Get All Fundings
    public List<FundingDTO> getAllFundings() {
        return fundingRepository.findAll()
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    // Get Funding by ID
    public Optional<FundingDTO> getFundingById(Long id) {
        return fundingRepository.findById(id).map(this::convertToDTO);
    }

    // Delete Funding
    public void deleteFunding(Long id) {
        fundingRepository.deleteById(id);
    }

}
