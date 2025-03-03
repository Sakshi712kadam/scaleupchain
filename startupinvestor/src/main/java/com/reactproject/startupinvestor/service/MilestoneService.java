package com.reactproject.startupinvestor.service;

import com.reactproject.startupinvestor.dto.MilestoneDTO;
import com.reactproject.startupinvestor.entities.Milestone;
import com.reactproject.startupinvestor.repositories.MilestoneRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MilestoneService {

    @Autowired
    private MilestoneRepository milestoneRepository;

    public MilestoneDTO saveMilestone(MilestoneDTO milestoneDTO) {
        Milestone milestone = new Milestone();
        milestone.setName(milestoneDTO.getName());
        milestone.setDeadline(milestoneDTO.getDeadline());
        milestone.setStatus(milestoneDTO.getStatus());

        milestone = milestoneRepository.save(milestone);

        milestoneDTO.setId(milestone.getId());
        return milestoneDTO;
    }

    public List<MilestoneDTO> getAllMilestones() {
        return milestoneRepository.findAll().stream().map(milestone -> {
            MilestoneDTO dto = new MilestoneDTO();
            dto.setId(milestone.getId());
            dto.setName(milestone.getName());
            dto.setDeadline(milestone.getDeadline());
            dto.setStatus(milestone.getStatus());
            return dto;
        }).collect(Collectors.toList());
    }

    public MilestoneDTO updateMilestone(Long id, MilestoneDTO milestoneDTO) {
        Milestone milestone = milestoneRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Milestone not found"));

        milestone.setName(milestoneDTO.getName());
        milestone.setDeadline(milestoneDTO.getDeadline());
        milestone.setStatus(milestoneDTO.getStatus());

        milestoneRepository.save(milestone);
        milestoneDTO.setId(milestone.getId());
        return milestoneDTO;
    }

    public void deleteMilestone(Long id) {
        if (!milestoneRepository.existsById(id)) {
            throw new RuntimeException("Milestone not found");
        }
        milestoneRepository.deleteById(id);
    }
}
