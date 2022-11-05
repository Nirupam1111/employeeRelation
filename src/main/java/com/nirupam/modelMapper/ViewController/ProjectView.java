package com.nirupam.modelMapper.ViewController;

import com.nirupam.modelMapper.dto.ProjectDto;
import com.nirupam.modelMapper.repository.DepartmentRepository;
import com.nirupam.modelMapper.repository.ProjectRepository;
import com.nirupam.modelMapper.serice.DepartmentService;
import com.nirupam.modelMapper.serice.ProjectService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ProjectView {
    private DepartmentRepository departmentRepository;
    private ProjectRepository projectRepository;
    private ProjectService projectService;
    private DepartmentService departmentService;

    public ProjectView(DepartmentService departmentService, ProjectService projectService, DepartmentRepository departmentRepository, ProjectRepository projectRepository) {
        this.departmentService = departmentService;
        this.departmentRepository = departmentRepository;
        this.projectRepository = projectRepository;
        this.projectService = projectService;
    }

    @GetMapping("/projects/about")
    public String index(Model model){
        model.addAttribute("projects", projectRepository.findAll());
        return "projectTemplate/index";
    }

    @GetMapping("/projects/form")
    public String signup(Model model){
        ProjectDto projectDto = new ProjectDto();
        model.addAttribute("projectDto", projectDto);
        model.addAttribute("departments", departmentRepository.findAll());
        return "projectTemplate/form";
    }

    @GetMapping("/projects/update/{id}")
    public String updateDetails(@PathVariable int id, Model model){
        ProjectDto projectDto = new ProjectDto();
        model.addAttribute("projectDto", projectDto);
        model.addAttribute("projectId", id);
        model.addAttribute("thisProject", projectRepository.findById(id).get());
        model.addAttribute("departments", departmentRepository.findAll());
        return "projectTemplate/updateDetails";
    }

    @PostMapping("/projects/submit")
    public String submit(@ModelAttribute ProjectDto projectDto){
        if(!projectService.findByName(projectDto.getProjectName()))
            projectService.addProject(projectDto);
        return "redirect:/projects/about";
    }

    @PostMapping("/projects/submitUpdate")
    public String submitUpdate(@ModelAttribute ProjectDto projectDto){
        projectService.updateProject(projectDto, projectDto.getId());
        return "redirect:/projects/about";
    }

    @GetMapping("/projects/delete/{id}")
    public String deleteDetails(@PathVariable int id){
        projectService.deleteProject(id);
        return "redirect:/projects/about";
    }

}
