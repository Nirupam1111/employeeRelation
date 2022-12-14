package com.nirupam.modelMapper.controller;

import com.nirupam.modelMapper.dto.DepartmentDto;
import com.nirupam.modelMapper.dto.DepartmentResponseDto;
import com.nirupam.modelMapper.serice.DepartmentService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@Api(value = "Department Contoller", tags = "get department,add department,update department,delete department",description = "details of department relation api")
public class DepartmentController {
    private DepartmentService departmentService;

    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    @GetMapping("/departments")
    @ApiOperation(value = "department details", notes = "get all Departments",response = ArrayList.class)
    public List<DepartmentResponseDto> getAllDepartments(){
        return departmentService.getAllDepartments();
    }

    @PostMapping("/departments")
    @ApiOperation(value = "add department", notes = "add Department", response = Void.class)
    public void addDepartment(@RequestBody DepartmentDto departmentDto) {
        departmentService.addDepartment(departmentDto);
    }

    @PutMapping("/departments/{id}")
    @ApiOperation(value = "update department", notes = "update Department", response = Void.class)
    public void updateDepartment(@RequestBody DepartmentDto departmentDto, @PathVariable int id) {
        departmentService.updateDepartment(departmentDto, id);
    }

    @DeleteMapping("/departments/{id}")
    @ApiOperation(value = "delete department details", notes = "delete department", response = Void.class)
    public void deleteDepartment(@PathVariable int id) {
        departmentService.deleteDepartment(id);
    }

}
