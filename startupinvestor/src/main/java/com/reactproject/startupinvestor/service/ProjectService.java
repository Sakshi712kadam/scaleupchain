package com.reactproject.startupinvestor.service;

import com.reactproject.startupinvestor.dto.ProjectDTO;
import com.reactproject.startupinvestor.entities.Project;
import com.reactproject.startupinvestor.repositories.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProjectService {
    @Autowired
    private ProjectRepository projectRepository;

    // Create a new project
    public Project createProject(ProjectDTO projectDTO) {
        Project project = new Project();
        project.setName(projectDTO.getName());
        project.setTeamSize(projectDTO.getTeamSize());
        project.setStartDate(projectDTO.getStartDate());
        project.setEndDate(projectDTO.getEndDate());
        project.setPercentageCompleted(projectDTO.getPercentageCompleted());
        return projectRepository.save(project);
    }

    // Get all projects
    public List<Project> getAllProjects() {
        return projectRepository.findAll();
    }

    // Get a project by ID
    public Optional<Project> getProjectById(Long id) {
        return projectRepository.findById(id);
    }

    // Update a project
    public Project updateProject(Long id, ProjectDTO projectDTO) {
        Optional<Project> existingProject = projectRepository.findById(id);
        if (existingProject.isPresent()) {
            Project project = existingProject.get();
            project.setName(projectDTO.getName());
            project.setTeamSize(projectDTO.getTeamSize());
            project.setStartDate(projectDTO.getStartDate());
            project.setEndDate(projectDTO.getEndDate());
            project.setPercentageCompleted(projectDTO.getPercentageCompleted());
            return projectRepository.save(project);
        }
        return null;  // or throw exception if not found
    }

    // Delete a project
    public void deleteProject(Long id) {
        projectRepository.deleteById(id);
    }
}
