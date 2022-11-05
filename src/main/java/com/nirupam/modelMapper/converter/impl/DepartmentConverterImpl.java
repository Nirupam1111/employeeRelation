package com.nirupam.modelMapper.converter.impl;

import com.nirupam.modelMapper.converter.DepartmentConverter;
import com.nirupam.modelMapper.dto.DepartmentDto;
import com.nirupam.modelMapper.dto.DepartmentResponseDto;
import com.nirupam.modelMapper.model.Department;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class DepartmentConverterImpl implements DepartmentConverter {
    public final ModelMapper modelMapper;

    public DepartmentConverterImpl(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @Override
    public Department departmentDtoToDepartment(DepartmentDto departmentDto) {
        return modelMapper.map(departmentDto, Department.class);
    }

    @Override
    public DepartmentResponseDto departmentToDepartmentResponseDto(Department department) {
        return modelMapper.map(department, DepartmentResponseDto.class);
    }

}
