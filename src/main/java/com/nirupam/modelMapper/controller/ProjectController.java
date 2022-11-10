package com.nirupam.modelMapper.controller;

import com.nirupam.modelMapper.dto.ProjectDto;
import com.nirupam.modelMapper.dto.ProjectResponseDto;
import com.nirupam.modelMapper.serice.ProjectService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@Api(value = "Project Contoller", tags = "get project,add project,update project,delete project",description = "details of project relation api")
public class ProjectController {
    private ProjectService projectService;

    public ProjectController(ProjectService projectService) {
        this.projectService = projectService;
    }

    @GetMapping("/projects")
    @ApiOperation(value = "project details", notes = "get all Projects",response = ArrayList.class)
    public List<ProjectResponseDto> getAllProjects(){
        return projectService.getAllProjects();
    }

    @PostMapping("/projects")
    @ApiOperation(value = "add project", notes = "add Projects", response = Void.class)
    public void addProject(@RequestBody ProjectDto projectDto) {
        projectService.addProject(projectDto);
    }

    @PutMapping("/projects/{id}")
    @ApiOperation(value = "update project", notes = "update Projects", response = Void.class)
    public void updateProject(@RequestBody ProjectDto projectDto, @PathVariable int id) {
        projectService.updateProject(projectDto, id);
    }

    @DeleteMapping("/projects/{id}")
    @ApiOperation(value = "delete project details", notes = "delete Projects", response = Void.class)
    public void deleteProject(@PathVariable int id) {
        projectService.deleteProject(id);
    }

}
