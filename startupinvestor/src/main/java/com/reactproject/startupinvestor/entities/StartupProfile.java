package com.reactproject.startupinvestor.entities;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Table(name = "startup_profile")
@Getter
@Setter
public class StartupProfile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String founderName;

    @Column(nullable = false)
    private String sector;

    @Column(nullable = false)
    private Long fundingRequirements;

    @Column(nullable = false)
    private String stage;

    @Column(nullable = false, length = 500)
    private String description;

    @Column(nullable = false, length = 500)
    private String visionMission;

    @Column(nullable = false)
    private Integer teamSize;

    @Column(nullable = false)
    private LocalDate foundedDate;


}
