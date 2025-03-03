package com.reactproject.startupinvestor.repositories;

import com.reactproject.startupinvestor.entities.Expenditure;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExpenditureRepository extends JpaRepository<Expenditure, Long> {
}
