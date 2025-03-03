package com.reactproject.startupinvestor.repositories;

import com.reactproject.startupinvestor.entities.Milestone;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MilestoneRepository extends JpaRepository<Milestone, Long> {
}
