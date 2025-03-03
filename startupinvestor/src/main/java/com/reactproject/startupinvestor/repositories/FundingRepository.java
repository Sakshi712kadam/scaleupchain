package com.reactproject.startupinvestor.repositories;

import com.reactproject.startupinvestor.entities.Funding;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FundingRepository extends JpaRepository<Funding, Long> {
}
