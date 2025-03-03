package com.reactproject.startupinvestor.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class DocumentDTO {

    private String title;
    private LocalDate date;
    private String description;

    // Getters and Setters
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
