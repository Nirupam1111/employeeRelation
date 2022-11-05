package com.nirupam.modelMapper.converter.impl;

import com.nirupam.modelMapper.converter.EmployeeConverter;
import com.nirupam.modelMapper.dto.EmployeeDto;
import com.nirupam.modelMapper.dto.EmployeeResponseDto;
import com.nirupam.modelMapper.model.Employee;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class EmployeeConverterImpl implements EmployeeConverter {
    public final ModelMapper modelMapper;

    public EmployeeConverterImpl(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @Override
    public Employee employeeDtoToEmployee(EmployeeDto employeeDto) {
        return modelMapper.map(employeeDto, Employee.class);
    }

    @Override
    public EmployeeResponseDto employeeToEmployeeResponseDto(Employee employee) {
        return modelMapper.map(employee, EmployeeResponseDto.class);
    }

}
