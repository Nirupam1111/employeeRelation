package com.nirupam.modelMapper.serice;

import com.nirupam.modelMapper.converter.ProjectConverter;
import com.nirupam.modelMapper.dto.ProjectDto;
import com.nirupam.modelMapper.dto.ProjectResponseDto;
import com.nirupam.modelMapper.model.Department;
import com.nirupam.modelMapper.model.Project;
import com.nirupam.modelMapper.repository.DepartmentRepository;
import com.nirupam.modelMapper.repository.ProjectRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProjectService {
    private ProjectRepository projectRepository;
    private DepartmentRepository departmentRepository;
    private ProjectConverter projectConverter;

    public ProjectService(ProjectRepository projectRepository, DepartmentRepository departmentRepository,
                          ProjectConverter projectConverter) {
        this.projectRepository = projectRepository;
        this.departmentRepository = departmentRepository;
        this.projectConverter = projectConverter;
    }

    public List<ProjectResponseDto> getAllProjects(){
        List<Project> projecteList = projectRepository.findAll();
        return projecteList.stream().map(projectConverter :: projectToProjectResponseDto).collect(Collectors.toList());
    }

    public void addProject(ProjectDto projectDto) {
        Project project = projectConverter.projectDtoToProject(projectDto);

        Optional<Department> dept = departmentRepository.findById(projectDto.getDepartment_id());
        if(dept.isEmpty())
            System.out.println("No department!!!");
        project.setDepartment(dept.get());

        projectRepository.save(project);
    }

    public void updateProject(ProjectDto projectDto, int id) {
        Project data = projectRepository.findById(id).orElse(null);
        assert data != null;
        List<Project> myProjects = projectRepository.findAll();
        for(Project myProject : myProjects)
            if(myProject.getProjectName().equals(projectDto.getProjectName())) return;
        data.setProjectName(projectDto.getProjectName());
        data.setDescription(projectDto.getDescription());
        data.setDepartment(departmentRepository.findById(projectDto.getDepartment_id()).get());
        projectRepository.save(data);
    }

    @Transactional
    public void deleteProject(int id) {
        projectRepository.deleteById(id);
    }

    public boolean findByName(String name) {
        Optional<Project> projectName = projectRepository.findByprojectName(name);
        return projectName.isPresent();
    }

}
