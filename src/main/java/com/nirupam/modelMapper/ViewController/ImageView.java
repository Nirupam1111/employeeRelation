package com.nirupam.modelMapper.ViewController;

import com.nirupam.modelMapper.dto.ImageDto;
import com.nirupam.modelMapper.model.Image;
import com.nirupam.modelMapper.repository.DepartmentRepository;
import com.nirupam.modelMapper.repository.EmployeeRepository;
import com.nirupam.modelMapper.repository.ImageRepository;
import com.nirupam.modelMapper.repository.ProjectRepository;
import com.nirupam.modelMapper.serice.ImageService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;

@Controller
public class ImageView {
    private ImageService imageService;
    private EmployeeRepository employeeRepository;
    private DepartmentRepository departmentRepository;
    private ProjectRepository projectRepository;
    private ImageRepository imageRepository;

    public ImageView(ImageService imageService, EmployeeRepository employeeRepository, DepartmentRepository departmentRepository,
                     ProjectRepository projectRepository, ImageRepository imageRepository) {
        this.imageService = imageService;
        this.employeeRepository = employeeRepository;
        this.departmentRepository = departmentRepository;
        this.projectRepository = projectRepository;
        this.imageRepository = imageRepository;
    }

    @GetMapping("/employees/about/{id}")
    public String index(@PathVariable int id, Model model){
        ImageDto img = new ImageDto();
        model.addAttribute("img", img);
        model.addAttribute("empId", id);
        model.addAttribute("thisEmployee", employeeRepository.findById(id).get());
        model.addAttribute("image", imageService.getImageByEmployeeId(id));
        return "employeeTemplate/profile";
    }

    @PostMapping("/employees/about/upload")
    public String index(@ModelAttribute ImageDto image){
        Optional<Image> optionalImage = imageRepository.findByEmployeeId(image.getEmployee_id());
        if(optionalImage.isPresent())
            imageService.updateImage(image);
        else
            imageService.saveImage(image);
        return "redirect:/employees/about/" + image.getEmployee_id();
    }

    @GetMapping("/employees/about/delete/{id}")
    public String delete(@PathVariable int id){
        Optional<Image> img = imageRepository.findByEmployeeId(id);
        if(img.isPresent()) {
            Path path = Paths.get("C:\\Users\\INDIA\\Downloads\\test\\" + img.get().getName());
            try{Files.delete(path);}catch(Exception e){};
            imageRepository.deleteByEmployeeId(id);
        }
        return "redirect:/employees/about/" + id;
    }

}
