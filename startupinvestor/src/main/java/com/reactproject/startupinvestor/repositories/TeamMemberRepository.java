package com.reactproject.startupinvestor.repositories;

import com.reactproject.startupinvestor.entities.TeamMember;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TeamMemberRepository extends JpaRepository<TeamMember, Long> {
}
