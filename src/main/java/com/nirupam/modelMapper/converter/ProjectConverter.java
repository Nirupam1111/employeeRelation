package com.nirupam.modelMapper.converter;

import com.nirupam.modelMapper.dto.ProjectDto;
import com.nirupam.modelMapper.dto.ProjectResponseDto;
import com.nirupam.modelMapper.model.Project;

public interface ProjectConverter {
    Project projectDtoToProject(ProjectDto projectDto);
    ProjectResponseDto projectToProjectResponseDto(Project project);
}
