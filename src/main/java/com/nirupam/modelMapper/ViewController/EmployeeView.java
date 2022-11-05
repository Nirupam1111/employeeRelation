package com.nirupam.modelMapper.ViewController;

import com.nirupam.modelMapper.dto.EmployeeDto;
import com.nirupam.modelMapper.repository.DepartmentRepository;
import com.nirupam.modelMapper.repository.EmployeeRepository;
import com.nirupam.modelMapper.repository.ImageRepository;
import com.nirupam.modelMapper.repository.ProjectRepository;
import com.nirupam.modelMapper.serice.EmployeeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class EmployeeView {
    private EmployeeRepository employeeRepository;
    private DepartmentRepository departmentRepository;
    private ProjectRepository projectRepository;
    private ImageRepository imageRepository;
    private EmployeeService employeeService;

    public EmployeeView(EmployeeRepository employeeRepository, DepartmentRepository departmentRepository,
                        ProjectRepository projectRepository, EmployeeService employeeService, ImageRepository imageRepository) {
        this.employeeRepository = employeeRepository;
        this.departmentRepository = departmentRepository;
        this.projectRepository = projectRepository;
        this.employeeService = employeeService;
        this.imageRepository = imageRepository;
    }

    @GetMapping("/frontpage")
    public String frontpage(Model model){
        return "/frontpage";
    }

    @GetMapping("/employees/about")
    public String index(Model model){
        model.addAttribute("employees", employeeRepository.findAll());
        return "employeeTemplate/index";
    }

    @GetMapping("/employees/signup")
    public String form(Model model){
        EmployeeDto employeeDto = new EmployeeDto();
        model.addAttribute("employeeDto", employeeDto);
        return "employeeTemplate/form";
    }

    @GetMapping("/employees/dropdown")
    public String dropDown(Model model){
        EmployeeDto employeeDto = new EmployeeDto();
        model.addAttribute("employeeDto", employeeDto);
        model.addAttribute("departments", departmentRepository.findAll());
        model.addAttribute("projects", projectRepository.findAll());
        return "employeeTemplate/dropDown";
    }

    @GetMapping("/employees/update/{id}")
    public String updateDetails(@PathVariable int id, Model model){
        EmployeeDto employeeDto = new EmployeeDto();
        model.addAttribute("employeeDto", employeeDto);
        model.addAttribute("empId", id);
        model.addAttribute("thisEmployee", employeeRepository.findById(id).get());
        model.addAttribute("departments", departmentRepository.findAll());
        model.addAttribute("projects", projectRepository.findAll());
        return "employeeTemplate/updateDetails";
    }

    @PostMapping("/employees/submit")
    public String submit(@ModelAttribute EmployeeDto employeeDto){
        employeeService.addEmployee(employeeDto);
        return "redirect:/employees/about";
    }

    @PostMapping("/employees/submitUpdate")
    public String submitUpdate(@ModelAttribute EmployeeDto employeeDto){
        employeeService.updateEmployee(employeeDto, employeeDto.getId());
        return "redirect:/employees/about";
    }

    @GetMapping("/employees/delete/{id}")
    public String deleteDetails(@PathVariable int id){
        imageRepository.deleteByEmployeeId(id);
        employeeService.deleteEmployee(id);
        return "redirect:/employees/about";
    }

}
