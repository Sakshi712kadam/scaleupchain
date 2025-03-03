package com.reactproject.startupinvestor.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "investor_profile")
@Getter
@Setter
public class InvestorProfile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String investorName;

    @Column(nullable = false)
    private Double minInvestmentAmount;

    @Column(nullable = false)
    private String preferredSector;
}