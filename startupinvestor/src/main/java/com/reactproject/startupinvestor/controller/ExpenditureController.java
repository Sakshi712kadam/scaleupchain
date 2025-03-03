package com.reactproject.startupinvestor.controller;

import com.reactproject.startupinvestor.dto.ExpenditureDTO;
import com.reactproject.startupinvestor.entities.Expenditure;
import com.reactproject.startupinvestor.service.ExpenditureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/expenditures")
public class ExpenditureController {
    @Autowired
    private ExpenditureService expenditureService;

    // Create a new expenditure (POST)
    @PostMapping
    public ResponseEntity<Expenditure> addExpenditure(@RequestBody ExpenditureDTO expenditureDTO) {
        Expenditure expenditure = expenditureService.createExpenditure(expenditureDTO);
        return new ResponseEntity<>(expenditure, HttpStatus.CREATED);
    }

    // Get all expenditures (GET)
    @GetMapping
    public ResponseEntity<List<Expenditure>> getAllExpenditures() {
        List<Expenditure> expenditures = expenditureService.getAllExpenditures();
        return new ResponseEntity<>(expenditures, HttpStatus.OK);
    }

    // Get an expenditure by ID (GET)
    @GetMapping("/{id}")
    public ResponseEntity<Expenditure> getExpenditureById(@PathVariable Long id) {
        Optional<Expenditure> expenditure = expenditureService.getExpenditureById(id);
        return expenditure.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    // Update an expenditure (PUT)
    @PutMapping("/{id}")
    public ResponseEntity<Expenditure> updateExpenditure(@PathVariable Long id, @RequestBody ExpenditureDTO expenditureDTO) {
        Expenditure updatedExpenditure = expenditureService.updateExpenditure(id, expenditureDTO);
        return updatedExpenditure != null ? ResponseEntity.ok(updatedExpenditure) : ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    // Delete an expenditure (DELETE)
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteExpenditure(@PathVariable Long id) {
        expenditureService.deleteExpenditure(id);
        return ResponseEntity.noContent().build();
    }
}
