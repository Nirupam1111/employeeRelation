package com.nirupam.modelMapper.repository;

import com.nirupam.modelMapper.model.Department;
import com.nirupam.modelMapper.model.Employee;
import com.nirupam.modelMapper.model.Project;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
    List<Employee> findAllBydepartment(Department department);
    List<Employee> findAllByprojects(Project project);
}
