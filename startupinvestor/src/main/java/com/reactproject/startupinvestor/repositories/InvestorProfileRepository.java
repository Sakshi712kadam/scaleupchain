package com.reactproject.startupinvestor.repositories;

import com.reactproject.startupinvestor.entities.InvestorProfile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InvestorProfileRepository extends JpaRepository<InvestorProfile , Long> {
}
