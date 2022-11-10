package com.nirupam.modelMapper.controller;

import com.nirupam.modelMapper.dto.ImageDto;
import com.nirupam.modelMapper.model.Image;
import com.nirupam.modelMapper.repository.DepartmentRepository;
import com.nirupam.modelMapper.repository.EmployeeRepository;
import com.nirupam.modelMapper.repository.ImageRepository;
import com.nirupam.modelMapper.repository.ProjectRepository;
import com.nirupam.modelMapper.serice.ImageService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

@RestController
@Api(value = "Image Contoller", tags = "get image,add image",description = "details of image relation api")
public class ImageController {
    private ImageService imageService;
    private EmployeeRepository employeeRepository;
    private DepartmentRepository departmentRepository;
    private ProjectRepository projectRepository;
    private ImageRepository imageRepository;

    public ImageController(ImageService imageService, EmployeeRepository employeeRepository, DepartmentRepository departmentRepository,
                     ProjectRepository projectRepository, ImageRepository imageRepository) {
        this.imageService = imageService;
        this.employeeRepository = employeeRepository;
        this.departmentRepository = departmentRepository;
        this.projectRepository = projectRepository;
        this.imageRepository = imageRepository;
    }

    @GetMapping("/employees/images")
    @ApiOperation(value = "image details", notes = "get all Images",response = ArrayList.class)
    public List<Image> index(){
        return imageRepository.findAll();
    }

    @PostMapping("/employees/images")
    @ApiOperation(value = "add image", notes = "add Images", response = Void.class)
    public void index(@RequestParam("file") MultipartFile file, @RequestParam int id){
        ImageDto image = new ImageDto();
        image.setProfileImg(file);
        image.setEmployee_id(id);
        imageService.saveImage(image);
    }

}
