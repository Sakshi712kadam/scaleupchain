package com.reactproject.startupinvestor.controller;

import com.reactproject.startupinvestor.dto.ProjectDTO;
import com.reactproject.startupinvestor.entities.Project;
import com.reactproject.startupinvestor.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/projects")
public class ProjectController {
    @Autowired
    private ProjectService projectService;

    // Create a new project
    @PostMapping
    public ResponseEntity<Project> createProject(@RequestBody ProjectDTO projectDTO) {
        Project project = projectService.createProject(projectDTO);
        return new ResponseEntity<>(project, HttpStatus.CREATED);
    }

    // Get all projects
    @GetMapping
    public ResponseEntity<List<Project>> getAllProjects() {
        List<Project> projects = projectService.getAllProjects();
        return new ResponseEntity<>(projects, HttpStatus.OK);
    }

    // Get a project by ID
    @GetMapping("/{id}")
    public ResponseEntity<Project> getProjectById(@PathVariable Long id) {
        Optional<Project> project = projectService.getProjectById(id);
        return project.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    // Update a project
    @PutMapping("/{id}")
    public ResponseEntity<Project> updateProject(@PathVariable Long id, @RequestBody ProjectDTO projectDTO) {
        Project updatedProject = projectService.updateProject(id, projectDTO);
        return updatedProject != null ? new ResponseEntity<>(updatedProject, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    // Delete a project
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProject(@PathVariable Long id) {
        projectService.deleteProject(id);
        return ResponseEntity.noContent().build();
    }
}
