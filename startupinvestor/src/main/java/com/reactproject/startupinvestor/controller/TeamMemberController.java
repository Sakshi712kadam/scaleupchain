package com.reactproject.startupinvestor.controller;

import com.reactproject.startupinvestor.dto.TeamMemberDTO;
import com.reactproject.startupinvestor.entities.TeamMember;
import com.reactproject.startupinvestor.service.TeamMemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/api/team-members")
public class TeamMemberController {

    @Autowired
    private TeamMemberService teamMemberService;

    @PostMapping
    public ResponseEntity<TeamMemberDTO> createTeamMember(@RequestBody TeamMemberDTO teamMemberDTO) {
        TeamMember teamMember = mapDtoToEntity(teamMemberDTO);
        TeamMember savedMember = teamMemberService.createTeamMember(teamMember);
        return ResponseEntity.ok(mapEntityToDto(savedMember));
    }

    @GetMapping
    public ResponseEntity<List<TeamMemberDTO>> getAllTeamMembers() {
        List<TeamMember> teamMembers = teamMemberService.getAllTeamMembers();
        return ResponseEntity.ok(teamMembers.stream().map(this::mapEntityToDto).toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<TeamMemberDTO> getTeamMemberById(@PathVariable Long id) {
        TeamMember teamMember = teamMemberService.getTeamMemberById(id);
        return ResponseEntity.ok(mapEntityToDto(teamMember));
    }

    @PutMapping("/{id}")
    public ResponseEntity<TeamMemberDTO> updateTeamMember(@PathVariable Long id, @RequestBody TeamMemberDTO teamMemberDTO) {
        TeamMember teamMember = mapDtoToEntity(teamMemberDTO);
        TeamMember updatedMember = teamMemberService.updateTeamMember(id, teamMember);
        return ResponseEntity.ok(mapEntityToDto(updatedMember));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteTeamMember(@PathVariable Long id) {
        teamMemberService.deleteTeamMember(id);
        return ResponseEntity.ok("Team member deleted successfully!");
    }

    private TeamMember mapDtoToEntity(TeamMemberDTO dto) {
        TeamMember teamMember = new TeamMember();
        teamMember.setId(dto.getId());
        teamMember.setName(dto.getName());
        teamMember.setJoiningDate(dto.getJoiningDate());
        teamMember.setPosition(dto.getPosition());
        return teamMember;
    }

    private TeamMemberDTO mapEntityToDto(TeamMember teamMember) {
        TeamMemberDTO dto = new TeamMemberDTO();
        dto.setId(teamMember.getId());
        dto.setName(teamMember.getName());
        dto.setJoiningDate(teamMember.getJoiningDate());
        dto.setPosition(teamMember.getPosition());
        return dto;
    }
}
