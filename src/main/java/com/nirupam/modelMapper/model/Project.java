package com.nirupam.modelMapper.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Project {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiModelProperty(name = "id",dataType = "int",example = "1")
    private int id;
    @ApiModelProperty(name = "ProjectName",dataType = "String",example = "Frontend Development")
    private String projectName;
    @ApiModelProperty(name = "description of project",dataType = "String",example = "Frontend Development with React")
    private String description;
    @ManyToOne
    @JoinColumn(name = "department_id")
    @ApiModelProperty(name = "department",dataType = "Object",example = "{'id': 1, 'deptName': developer, 'location': kolkata}")
    private Department department;
    @JsonIgnore
    @ManyToMany(mappedBy = "projects")
    private List<Employee> employees = new ArrayList<>();

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public List<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(List<Employee> employees) {
        this.employees = employees;
    }
}
