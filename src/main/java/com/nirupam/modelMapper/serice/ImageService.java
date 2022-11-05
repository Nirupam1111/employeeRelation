package com.nirupam.modelMapper.serice;

import com.nirupam.modelMapper.dto.ImageDto;
import com.nirupam.modelMapper.model.Image;
import com.nirupam.modelMapper.repository.EmployeeRepository;
import com.nirupam.modelMapper.repository.ImageRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Base64;
import java.util.List;
import java.util.Optional;

@Service
public class ImageService {
    private ImageRepository imageRepository;
    private EmployeeRepository employeeRepository;

    public ImageService(ImageRepository imageRepository, EmployeeRepository employeeRepository) {
        this.imageRepository = imageRepository;
        this.employeeRepository = employeeRepository;
    }

    public void saveImage(ImageDto imageDto){
        MultipartFile file = imageDto.getProfileImg();
        Image img = new Image();
        try {
            img.setProfileImg(Base64.getEncoder().encodeToString(file.getBytes()));

            Path path = Paths.get("C:\\Users\\INDIA\\Downloads\\test");
            if (!Files.isDirectory(path))
                Files.createDirectory(path);
            path = Paths.get(path + "\\" + file.getOriginalFilename());
            file.transferTo(path);
            img.setName(file.getOriginalFilename());
            img.setSize(String.valueOf(file.getSize()));
            img.setType(file.getContentType());

        } catch (Exception e) {
            e.printStackTrace();
        }
        img.setEmployee(employeeRepository.findById(imageDto.getEmployee_id()).get());
        imageRepository.save(img);
    }

    public List<Image> getAllImages() {
        return imageRepository.findAll();
    }

    public Image getImageByEmployeeId(int id) {
        Optional<Image> img = imageRepository.findByEmployeeId(id);
        if(img.isPresent())
            return img.get();
        return null;
    }

    public void updateImage(ImageDto image) {
        Optional<Image> optionalImage = imageRepository.findByEmployeeId(image.getEmployee_id());
        Image newImage = optionalImage.get();
        MultipartFile file = image.getProfileImg();
        try {
            newImage.setProfileImg(Base64.getEncoder().encodeToString(file.getBytes()));

            Path path = Paths.get("C:\\Users\\INDIA\\Downloads\\test\\" + file.getOriginalFilename());
            Path previousPath = Paths.get("C:\\Users\\INDIA\\Downloads\\test\\" +
                    imageRepository.findByEmployeeId(image.getEmployee_id()).get().getName());
            Files.delete(previousPath);
            file.transferTo(path);
            newImage.setName(file.getOriginalFilename());
            newImage.setSize(String.valueOf(file.getSize()));
            newImage.setType(file.getContentType());

        }catch(Exception e){
            e.printStackTrace();
        }
        newImage.setEmployee(employeeRepository.findById(image.getEmployee_id()).get());
        imageRepository.save(newImage);
    }

}
