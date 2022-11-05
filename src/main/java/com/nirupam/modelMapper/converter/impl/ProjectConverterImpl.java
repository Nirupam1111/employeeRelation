package com.nirupam.modelMapper.converter.impl;

import com.nirupam.modelMapper.converter.ProjectConverter;
import com.nirupam.modelMapper.dto.ProjectDto;
import com.nirupam.modelMapper.dto.ProjectResponseDto;
import com.nirupam.modelMapper.model.Project;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class ProjectConverterImpl implements ProjectConverter {
    public final ModelMapper modelMapper;

    public ProjectConverterImpl(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @Override
    public Project projectDtoToProject(ProjectDto projectDto) {
        return modelMapper.map(projectDto, Project.class);
    }

    @Override
    public ProjectResponseDto projectToProjectResponseDto(Project project) {
        return modelMapper.map(project, ProjectResponseDto.class);
    }

}
