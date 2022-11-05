package com.nirupam.modelMapper.controller;

import com.nirupam.modelMapper.dto.DepartmentDto;
import com.nirupam.modelMapper.dto.DepartmentResponseDto;
import com.nirupam.modelMapper.serice.DepartmentService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class DepartmentController {
    private DepartmentService departmentService;

    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    @GetMapping("/departments")
    public List<DepartmentResponseDto> getAllDepartments(){
        return departmentService.getAllDepartments();
    }

    @PostMapping("/departments")
    public void addDepartment(@RequestBody DepartmentDto departmentDto) {
        departmentService.addDepartment(departmentDto);
    }

    @PutMapping("/departments/{id}")
    public void updateDepartment(@RequestBody DepartmentDto departmentDto, @PathVariable int id) {
        departmentService.updateDepartment(departmentDto, id);
    }

    @DeleteMapping("/departments/{id}")
    public void deleteDepartment(@PathVariable int id) {
        departmentService.deleteDepartment(id);
    }

}
