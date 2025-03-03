package com.reactproject.startupinvestor.repositories;

import com.reactproject.startupinvestor.entities.Project;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProjectRepository extends JpaRepository<Project, Long> {
}
