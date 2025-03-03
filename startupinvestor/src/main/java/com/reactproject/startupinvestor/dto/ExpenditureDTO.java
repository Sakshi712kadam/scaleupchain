package com.reactproject.startupinvestor.dto;

import lombok.Data;

@Data
public class ExpenditureDTO {
    private Double amount;
    private String description;

    // Constructors, getters, and setters

    public ExpenditureDTO() {
    }

    public ExpenditureDTO(Double amount, String description) {
        this.amount = amount;
        this.description = description;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
