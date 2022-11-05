package com.nirupam.modelMapper.repository;

import com.nirupam.modelMapper.model.Project;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProjectRepository extends JpaRepository<Project, Integer> {
    Optional<Project> findByprojectName(String name);
}
