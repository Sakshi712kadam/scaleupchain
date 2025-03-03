package com.reactproject.startupinvestor.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class StartupProfileDto {

    private String name;
    private String founderName;
    private String sector;
    private Long fundingRequirements;
    private String stage;
    private String description;
    private String visionMission;
    private Integer teamSize;
    private LocalDate foundedDate;

}