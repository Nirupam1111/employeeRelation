package com.nirupam.modelMapper.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@ApiModel(value = "Employee model", description = "model of employees")
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @ApiModelProperty(name = "id",dataType = "int",example = "1")
    private int id;
    @ApiModelProperty(name = "name",dataType = "String",example = "Nirupam Sur")
    private String name;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "address_id")
    @ApiModelProperty(name = "address",dataType = "Object",example = "{'id': 1, 'city': Panskura, 'state': WB}")
    private Address address;
    @ApiModelProperty(name = "date-of-birth",dataType = "String",example = "21-10-2002")
    private String dob;
    @ApiModelProperty(name = "date-of-joining",dataType = "String",example = "25-10-2022")
    private String doj;
    @ManyToOne
    @JoinColumn(name = "department_id")
    @ApiModelProperty(name = "department",dataType = "Object",example = "{'id': 1, 'deptName': developer, 'location': kolkata}")
    private Department department;
    @ManyToMany
    @JoinTable(name = "Employee_Project", joinColumns = {@JoinColumn(name = "employee_id", referencedColumnName = "id")},
                inverseJoinColumns = {@JoinColumn(name = "project_id")})
    @ApiModelProperty(name = "projects",dataType = "List<Object>",example = "{'id': 1, 'projectName': web-design, 'description': design a website}")
    private List<Project> projects = new ArrayList<>();
    @JsonIgnore
    @OneToOne(mappedBy = "employee")
    private Image image;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getDoj() {
        return doj;
    }

    public void setDoj(String doj) {
        this.doj = doj;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public List<Project> getProjects() {
        return projects;
    }

    public void setProjects(List<Project> projects) {
        this.projects = projects;
    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }
}
