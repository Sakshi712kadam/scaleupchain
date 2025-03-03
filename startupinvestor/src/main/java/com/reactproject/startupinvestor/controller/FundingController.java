package com.reactproject.startupinvestor.controller;

import com.reactproject.startupinvestor.dto.FundingDTO;
import com.reactproject.startupinvestor.service.FundingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/funding")
public class FundingController {

    @Autowired
    private FundingService fundingService;

    // Create or Update Funding
    @PostMapping
    public ResponseEntity<FundingDTO> saveFunding(@RequestBody FundingDTO fundingDTO) {
        FundingDTO savedFunding = fundingService.saveFunding(fundingDTO);
        return ResponseEntity.ok(savedFunding);
    }

    // Get All Fundings
    @GetMapping
    public ResponseEntity<List<FundingDTO>> getAllFundings() {
        List<FundingDTO> fundingList = fundingService.getAllFundings();
        return ResponseEntity.ok(fundingList);
    }


    // Get Funding by ID
    @GetMapping("/{id}")
    public ResponseEntity<FundingDTO> getFundingById(@PathVariable Long id) {
        Optional<FundingDTO> funding = fundingService.getFundingById(id);
        return funding.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Delete Funding
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteFunding(@PathVariable Long id) {
        fundingService.deleteFunding(id);
        return ResponseEntity.noContent().build();
    }

}
