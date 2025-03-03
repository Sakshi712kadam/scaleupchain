package com.reactproject.startupinvestor.repositories;

import com.reactproject.startupinvestor.entities.StartupProfile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StartupProfileRepository extends JpaRepository<StartupProfile , Long> {
}
