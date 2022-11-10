package com.nirupam.modelMapper.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;
import java.util.List;

@Entity
public class Department {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiModelProperty(name = "id",dataType = "int",example = "1")
    private int id;
    @ApiModelProperty(name = "department name",dataType = "String",example = "developer")
    private String deptName;
    @ApiModelProperty(name = "location",dataType = "String",example = "Kolkata")
    private String location;
    @JsonIgnore
    @OneToMany(mappedBy = "department")
    private List<Employee> employees;
    @JsonIgnore
    @OneToMany(mappedBy = "department")
    private List<Project> projects;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public List<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(List<Employee> employees) {
        this.employees = employees;
    }

    public List<Project> getProjects() {
        return projects;
    }

    public void setProjects(List<Project> projects) {
        this.projects = projects;
    }

}
