package com.nirupam.modelMapper.converter;

import com.nirupam.modelMapper.dto.EmployeeDto;
import com.nirupam.modelMapper.dto.EmployeeResponseDto;
import com.nirupam.modelMapper.model.Employee;

public interface EmployeeConverter {
    Employee employeeDtoToEmployee(EmployeeDto employeeDto);
    EmployeeResponseDto employeeToEmployeeResponseDto(Employee employee);
}
