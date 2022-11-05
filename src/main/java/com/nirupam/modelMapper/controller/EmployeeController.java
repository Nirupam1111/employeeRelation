package com.nirupam.modelMapper.controller;

import com.nirupam.modelMapper.dto.EmployeeDto;
import com.nirupam.modelMapper.dto.EmployeeResponseDto;
import com.nirupam.modelMapper.model.Employee;
import com.nirupam.modelMapper.serice.EmployeeService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class EmployeeController {
    private EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping("/employees")
    public List<EmployeeResponseDto> getAllEmployees(){
        return employeeService.getAllEmployees();
    }

    @PostMapping("/employees")
    public void addEmployee(@RequestBody EmployeeDto employeeDto) {
        employeeService.addEmployee(employeeDto);
    }

    @PutMapping("/employees/{id}")
    public void updateEmployee(@RequestBody EmployeeDto employeeDto, @PathVariable int id) {
        employeeService.updateEmployee(employeeDto, id);
    }

    @DeleteMapping("/employees/{id}")
    public void deleteEmployee(@PathVariable int id) {
        employeeService.deleteEmployee(id);
    }

    @GetMapping("/employees/dept/{name}")
    public List<Employee> getAllByDepartment(@PathVariable String name){
        return employeeService.getAllByDepartment(name);
    }

    @GetMapping("/employees/project/{name}")
    public List<Employee> getAllByprojects(@PathVariable String name){
        return employeeService.findAllByprojects(name);
    }

}
