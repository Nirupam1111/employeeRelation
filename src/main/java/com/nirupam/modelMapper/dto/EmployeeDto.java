package com.nirupam.modelMapper.dto;

import com.nirupam.modelMapper.model.Address;

import java.util.List;

public class EmployeeDto {
    private int id;
    private String name;
    private Address address;
    private String dob;
    private String doj;
    private int department_id;
    private List<TestDto> project_list;

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

    public int getDepartment_id() {
        return department_id;
    }

    public void setDepartment_id(int department_id) {
        this.department_id = department_id;
    }

    public List<TestDto> getProject_list() {
        return project_list;
    }

    public void setProject_list(List<TestDto> project_list) {
        this.project_list = project_list;
    }
}
