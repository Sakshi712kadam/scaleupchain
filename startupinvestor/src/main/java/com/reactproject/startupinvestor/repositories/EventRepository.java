package com.reactproject.startupinvestor.repositories;

import com.reactproject.startupinvestor.entities.Event;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventRepository extends JpaRepository<Event, Long> {
}
