package com.reactproject.startupinvestor.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class InvestorProfileDto {

    private String investorName;
    private Double minInvestmentAmount;
    private String preferredSector;

}