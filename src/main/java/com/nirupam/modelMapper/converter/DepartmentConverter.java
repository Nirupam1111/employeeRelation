package com.nirupam.modelMapper.converter;

import com.nirupam.modelMapper.dto.DepartmentDto;
import com.nirupam.modelMapper.dto.DepartmentResponseDto;
import com.nirupam.modelMapper.model.Department;

public interface DepartmentConverter {
    Department departmentDtoToDepartment(DepartmentDto departmentDto);
    DepartmentResponseDto departmentToDepartmentResponseDto(Department department);
}
