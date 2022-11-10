package com.nirupam.modelMapper.controller;

import com.nirupam.modelMapper.dto.EmployeeDto;
import com.nirupam.modelMapper.dto.EmployeeResponseDto;
import com.nirupam.modelMapper.model.Employee;
import com.nirupam.modelMapper.serice.EmployeeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@Api(value = "Employee Contoller", tags = "get eomployee,add eomployee,update eomployee,delete eomployee",description = "details of employee relation api")
public class EmployeeController {
    private EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping("/employees")
    @ApiOperation(value = "employee details", notes = "get all Employees",response = ArrayList.class)
    public List<EmployeeResponseDto> getAllEmployees(){
        return employeeService.getAllEmployees();
    }

    @PostMapping("/employees")
    @ApiOperation(value = "add employee", notes = "add Employees", response = Void.class)
    public void addEmployee(@RequestBody EmployeeDto employeeDto) {
        employeeService.addEmployee(employeeDto);
    }

    @PutMapping("/employees/{id}")
    @ApiOperation(value = "update employee", notes = "update Employees", response = Void.class)
    public void updateEmployee(@RequestBody EmployeeDto employeeDto, @PathVariable int id) {
        employeeService.updateEmployee(employeeDto, id);
    }

    @DeleteMapping("/employees/{id}")
    @ApiOperation(value = "delete employee details", notes = "delete Employees", response = Void.class)
    public void deleteEmployee(@PathVariable int id) {
        employeeService.deleteEmployee(id);
    }

    @GetMapping("/employees/dept/{name}")
    @ApiOperation(value = "employee details ", notes = "get all Employees By Department Name",response = ArrayList.class)
    public List<Employee> getAllByDepartment(@PathVariable String name){
        return employeeService.getAllByDepartment(name);
    }

    @GetMapping("/employees/project/{name}")
    @ApiOperation(value = "employee details ", notes = "get all Employees By Project Name",response = ArrayList.class)
    public List<Employee> getAllByprojects(@PathVariable String name){
        return employeeService.findAllByprojects(name);
    }

}
