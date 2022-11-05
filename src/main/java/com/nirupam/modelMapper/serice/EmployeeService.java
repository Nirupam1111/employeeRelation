package com.nirupam.modelMapper.serice;

import com.nirupam.modelMapper.converter.EmployeeConverter;
import com.nirupam.modelMapper.dto.EmployeeDto;
import com.nirupam.modelMapper.dto.EmployeeResponseDto;
import com.nirupam.modelMapper.dto.TestDto;
import com.nirupam.modelMapper.model.Department;
import com.nirupam.modelMapper.model.Employee;
import com.nirupam.modelMapper.model.Project;
import com.nirupam.modelMapper.repository.DepartmentRepository;
import com.nirupam.modelMapper.repository.EmployeeRepository;
import com.nirupam.modelMapper.repository.ProjectRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class EmployeeService {
    private EmployeeRepository employeeRepository;
    private DepartmentRepository departmentRepository;
    private ProjectRepository projectRepository;
    private EmployeeConverter employeeConverter;


    public EmployeeService(EmployeeRepository employeeRepository, DepartmentRepository departmentRepository,
                           ProjectRepository projectRepository, EmployeeConverter employeeConverter) {
        this.employeeRepository = employeeRepository;
        this.departmentRepository = departmentRepository;
        this.projectRepository = projectRepository;
        this.employeeConverter = employeeConverter;
    }

    public List<EmployeeResponseDto> getAllEmployees(){
        List<Employee> employeeList = employeeRepository.findAll();
        return employeeList.stream().map(employeeConverter :: employeeToEmployeeResponseDto).collect(Collectors.toList());
    }

    public void addEmployee(EmployeeDto employeeDto) {
        Employee employee = employeeConverter.employeeDtoToEmployee(employeeDto);

        Optional<Department> dept = departmentRepository.findById(employeeDto.getDepartment_id());
        if(dept.isEmpty())
            System.out.println("No department joined!!!");
        employee.setDepartment(dept.get());

        List<TestDto> projectList = employeeDto.getProject_list();
        for(TestDto project : projectList){
            Optional<Project> optionalProject = projectRepository.findById(project.getProject_id());
            employee.getProjects().add(optionalProject.get());
        }

        employeeRepository.save(employee);
    }

    public void updateEmployee(EmployeeDto employeeDto, int id) {
        Optional<Employee> thisStudent = employeeRepository.findById(id);
        if(thisStudent.isEmpty())
            System.out.println("No Employee found!!!");
        Employee data = thisStudent.get();
        data.setName(employeeDto.getName());
        data.setAddress(employeeDto.getAddress());
        data.setDob(employeeDto.getDob());
        data.setDoj(employeeDto.getDoj());
        data.setDepartment(departmentRepository.findById(employeeDto.getDepartment_id()).get());

        List<Project> emptyList = new ArrayList<>();
        data.setProjects(emptyList);
        List<TestDto> getProjects = employeeDto.getProject_list();
        for (TestDto project : getProjects){
            Optional<Project> optionalProject = projectRepository.findById(project.getProject_id());
            data.getProjects().add(optionalProject.get());
        }

        employeeRepository.save(data);
    }

    public void deleteEmployee(int id) {
        employeeRepository.deleteById(id);
    }

    public List<Employee> getAllByDepartment(String name){
        Optional<Department> department = departmentRepository.findBydeptName(name);
        if(department.isEmpty())
            return  null;
        return employeeRepository.findAllBydepartment(department.get());
    }

    public List<Employee> findAllByprojects(String name){
        Optional<Project> project = projectRepository.findByprojectName(name);
        if(project.isEmpty())
            return  null;
        return employeeRepository.findAllByprojects(project.get());
    }

}
