package com.reactproject.startupinvestor.repositories;

import com.reactproject.startupinvestor.entities.Document;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DocumentRepository extends JpaRepository<Document, Long> {
}
