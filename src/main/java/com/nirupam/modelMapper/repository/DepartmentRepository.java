package com.nirupam.modelMapper.repository;

import com.nirupam.modelMapper.model.Department;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface DepartmentRepository extends JpaRepository<Department, Integer> {
    Optional<Department> findBydeptName(String name);
    //Department findBydeptName(String name);
}
