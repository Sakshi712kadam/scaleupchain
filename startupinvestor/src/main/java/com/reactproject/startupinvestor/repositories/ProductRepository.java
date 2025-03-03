package com.reactproject.startupinvestor.repositories;

import com.reactproject.startupinvestor.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
