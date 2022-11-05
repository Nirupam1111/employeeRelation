package com.nirupam.modelMapper.serice;

import com.nirupam.modelMapper.converter.DepartmentConverter;
import com.nirupam.modelMapper.dto.DepartmentDto;
import com.nirupam.modelMapper.dto.DepartmentResponseDto;
import com.nirupam.modelMapper.model.Department;
import com.nirupam.modelMapper.repository.DepartmentRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class DepartmentService {
    private DepartmentRepository departmentRepository;
    private DepartmentConverter departmentConverter;

    public DepartmentService(DepartmentRepository departmentRepository, DepartmentConverter departmentConverter) {
        this.departmentRepository = departmentRepository;
        this.departmentConverter = departmentConverter;
    }

    public List<DepartmentResponseDto> getAllDepartments(){
        List<Department> departmentList = departmentRepository.findAll();
        return departmentList.stream().map(departmentConverter :: departmentToDepartmentResponseDto).collect(Collectors.toList());
    }

    public void addDepartment(DepartmentDto departmentDto) {
        Department department = departmentConverter.departmentDtoToDepartment(departmentDto);
        departmentRepository.save(department);
    }

    public void updateDepartment(DepartmentDto department, int id) {
        Department data = departmentRepository.findById(id).orElse(null);
        assert data != null;
        List<Department> myDepartments = departmentRepository.findAll();
        for(Department myDepartment : myDepartments)
            if(myDepartment.getDeptName().equals(department.getDeptName())) return;
        data.setDeptName(department.getDeptName());
        data.setLocation(department.getLocation());
        departmentRepository.save(data);
    }

    public void deleteDepartment(int id) {
        departmentRepository.deleteById(id);
    }

    public boolean findByName(String name) {
        Optional<Department> deptName = departmentRepository.findBydeptName(name);
        return deptName.isPresent();
    }

}
