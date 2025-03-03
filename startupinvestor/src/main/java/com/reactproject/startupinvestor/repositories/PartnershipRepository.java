package com.reactproject.startupinvestor.repositories;

import com.reactproject.startupinvestor.entities.Partnership;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PartnershipRepository extends JpaRepository<Partnership, Long> {
}
