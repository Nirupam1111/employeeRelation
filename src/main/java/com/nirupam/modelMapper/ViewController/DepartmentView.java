package com.nirupam.modelMapper.ViewController;

import com.nirupam.modelMapper.dto.DepartmentDto;
import com.nirupam.modelMapper.repository.DepartmentRepository;
import com.nirupam.modelMapper.repository.ProjectRepository;
import com.nirupam.modelMapper.serice.DepartmentService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class DepartmentView {
    private DepartmentRepository departmentRepository;
    private ProjectRepository projectRepository;
    private DepartmentService departmentService;

    public DepartmentView(DepartmentService departmentService, DepartmentRepository departmentRepository, ProjectRepository projectRepository) {
        this.departmentService = departmentService;
        this.departmentRepository = departmentRepository;
        this.projectRepository = projectRepository;
    }

    @GetMapping("/departments/about")
    public String index(Model model){
        model.addAttribute("departments", departmentRepository.findAll());
        return "departmentTemplate/index";
    }

    @GetMapping("/departments/form")
    public String form(Model model){
        DepartmentDto departmentDto = new DepartmentDto();
        model.addAttribute("departmentDto", departmentDto);
        return "departmentTemplate/form";
    }

    @GetMapping("/departments/update/{id}")
    public String updateDetails(@PathVariable int id, Model model){
        DepartmentDto departmentDto = new DepartmentDto();
        model.addAttribute("departmentDto", departmentDto);
        model.addAttribute("deptId", id);
        model.addAttribute("thisDepartment", departmentRepository.findById(id).get());
        return "departmentTemplate/updateDetails";
    }

    @PostMapping("/departments/submit")
    public String submit(@ModelAttribute DepartmentDto departmentDto){
        if(!departmentService.findByName(departmentDto.getDeptName()))
            departmentService.addDepartment(departmentDto);
        return "redirect:/departments/about";
    }

    @PostMapping("/departments/submitUpdate")
    public String submitUpdate(@ModelAttribute DepartmentDto departmentDto){
        departmentService.updateDepartment(departmentDto, departmentDto.getId());
        return "redirect:/departments/about";
    }

    @GetMapping("/departments/delete/{id}")
    public String deleteDetails(@PathVariable int id){
        departmentService.deleteDepartment(id);
        return "redirect:/departments/about";
    }

}
