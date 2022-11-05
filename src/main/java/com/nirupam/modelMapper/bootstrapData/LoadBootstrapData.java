package com.nirupam.modelMapper.bootstrapData;

import com.nirupam.modelMapper.repository.DepartmentRepository;
import com.nirupam.modelMapper.repository.EmployeeRepository;
import com.nirupam.modelMapper.repository.ProjectRepository;
import org.springframework.boot.CommandLineRunner;

public class LoadBootstrapData implements CommandLineRunner {
    private EmployeeRepository employeeRepository;
    private DepartmentRepository departmentRepository;
    private ProjectRepository projectRepository;

    public LoadBootstrapData(EmployeeRepository employeeRepository,
                            DepartmentRepository departmentRepository,
                            ProjectRepository projectRepository) {
        this.employeeRepository = employeeRepository;
        this.departmentRepository = departmentRepository;
        this.projectRepository = projectRepository;
    }

    @Override
    public void run(String... args) throws Exception {}

}
