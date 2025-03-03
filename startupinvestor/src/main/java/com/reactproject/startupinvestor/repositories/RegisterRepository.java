package com.reactproject.startupinvestor.repositories;

import com.reactproject.startupinvestor.entities.RegisterEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RegisterRepository extends JpaRepository<RegisterEntity, Long> {

    boolean existsByEmail(String email); // To check if a user already exists
    Optional<RegisterEntity> findByEmail(String email);
}
