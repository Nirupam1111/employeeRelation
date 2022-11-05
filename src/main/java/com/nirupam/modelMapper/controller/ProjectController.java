package com.nirupam.modelMapper.controller;

import com.nirupam.modelMapper.dto.ProjectDto;
import com.nirupam.modelMapper.dto.ProjectResponseDto;
import com.nirupam.modelMapper.serice.ProjectService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ProjectController {
    private ProjectService projectService;

    public ProjectController(ProjectService projectService) {
        this.projectService = projectService;
    }

    @GetMapping("/projects")
    public List<ProjectResponseDto> getAllProjects(){
        return projectService.getAllProjects();
    }

    @PostMapping("/projects")
    public void addProject(@RequestBody ProjectDto projectDto) {
        projectService.addProject(projectDto);
    }

    @PutMapping("/projects/{id}")
    public void updateProject(@RequestBody ProjectDto projectDto, @PathVariable int id) {
        projectService.updateProject(projectDto, id);
    }

    @DeleteMapping("/projects/{id}")
    public void deleteProject(@PathVariable int id) {
        projectService.deleteProject(id);
    }

}
