package com.reactproject.startupinvestor.service;

import com.reactproject.startupinvestor.dto.ExpenditureDTO;
import com.reactproject.startupinvestor.entities.Expenditure;
import com.reactproject.startupinvestor.repositories.ExpenditureRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ExpenditureService {
    @Autowired
    private ExpenditureRepository expenditureRepository;

    // Create a new expenditure
    public Expenditure createExpenditure(ExpenditureDTO expenditureDTO) {
        Expenditure expenditure = new Expenditure(expenditureDTO.getAmount(), expenditureDTO.getDescription());
        return expenditureRepository.save(expenditure);
    }

    // Get all expenditures
    public List<Expenditure> getAllExpenditures() {
        return expenditureRepository.findAll();
    }

    // Get an expenditure by ID
    public Optional<Expenditure> getExpenditureById(Long id) {
        return expenditureRepository.findById(id);
    }

    // Update an existing expenditure
    public Expenditure updateExpenditure(Long id, ExpenditureDTO expenditureDTO) {
        Optional<Expenditure> existingExpenditure = expenditureRepository.findById(id);
        if (existingExpenditure.isPresent()) {
            Expenditure expenditure = existingExpenditure.get();
            expenditure.setAmount(expenditureDTO.getAmount());
            expenditure.setDescription(expenditureDTO.getDescription());
            return expenditureRepository.save(expenditure);
        }
        return null;
    }

    // Delete an expenditure by ID
    public void deleteExpenditure(Long id) {
        expenditureRepository.deleteById(id);
    }

}
