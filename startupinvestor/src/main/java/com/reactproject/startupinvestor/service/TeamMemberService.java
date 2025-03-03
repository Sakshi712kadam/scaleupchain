package com.reactproject.startupinvestor.service;

import com.reactproject.startupinvestor.entities.TeamMember;
import com.reactproject.startupinvestor.repositories.TeamMemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TeamMemberService {

    @Autowired
    private TeamMemberRepository teamMemberRepository;

    public TeamMember createTeamMember(TeamMember teamMember) {
        return teamMemberRepository.save(teamMember);
    }

    public List<TeamMember> getAllTeamMembers() {
        return teamMemberRepository.findAll();
    }

    public TeamMember getTeamMemberById(Long id) {
        return teamMemberRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Team member not found with id: " + id));
    }

    public TeamMember updateTeamMember(Long id, TeamMember updatedMember) {
        TeamMember existingMember = getTeamMemberById(id);
        existingMember.setName(updatedMember.getName());
        existingMember.setJoiningDate(updatedMember.getJoiningDate());
        existingMember.setPosition(updatedMember.getPosition());
        return teamMemberRepository.save(existingMember);
    }

    public void deleteTeamMember(Long id) {
        teamMemberRepository.deleteById(id);
    }
}
