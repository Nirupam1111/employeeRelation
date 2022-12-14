package com.nirupam.modelMapper.serice;

import com.nirupam.modelMapper.dto.ImageDto;
import com.nirupam.modelMapper.model.Image;
import com.nirupam.modelMapper.repository.EmployeeRepository;
import com.nirupam.modelMapper.repository.ImageRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
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
            //img.setProfileImg(Base64.getEncoder().encodeToString(file.getBytes()));

            Path path = Paths.get("C:\\Users\\INDIA\\Downloads\\test");
            if (!Files.isDirectory(path))
                Files.createDirectory(path);
            path = Paths.get(path + "\\" + file.getOriginalFilename());
            file.transferTo(path);

            img.setProfileImg(path.toString());
            img.setName(file.getOriginalFilename());
            img.setSize(String.valueOf(file.getSize()));
            img.setType(file.getContentType());
            img.setEmployee(employeeRepository.findById(imageDto.getEmployee_id()).get());

        } catch (Exception e) {
            e.printStackTrace();
        }
        imageRepository.save(img);
    }

    public List<Image> getAllImages() {
        return imageRepository.findAll();
    }

    public String getImageByEmployeeId(int id) throws IOException {
        Optional<Image> img = imageRepository.findByEmployeeId(id);
        File file = new File(img.get().getProfileImg());
        BufferedImage bI = ImageIO.read(file);

        ByteArrayOutputStream bToAry = new ByteArrayOutputStream();
        ImageIO.write(bI, "jpg", bToAry);
        byte[] data = bToAry.toByteArray();

        String image = Base64.getEncoder().encodeToString(data);
        return image;
    }

    public void updateImage(ImageDto image) {
        Optional<Image> optionalImage = imageRepository.findByEmployeeId(image.getEmployee_id());
        Image newImage = optionalImage.get();
        MultipartFile file = image.getProfileImg();
        try {
            //newImage.setProfileImg(Base64.getEncoder().encodeToString(file.getBytes()));

            Path path = Paths.get("C:\\Users\\INDIA\\Downloads\\test\\" + file.getOriginalFilename());
            Path previousPath = Paths.get("C:\\Users\\INDIA\\Downloads\\test\\" +
                    imageRepository.findByEmployeeId(image.getEmployee_id()).get().getName());
            Files.delete(previousPath);
            file.transferTo(path);
            newImage.setProfileImg(path.toString());
            newImage.setName(file.getOriginalFilename());
            newImage.setSize(String.valueOf(file.getSize()));
            newImage.setType(file.getContentType());
            newImage.setEmployee(employeeRepository.findById(image.getEmployee_id()).get());

        }catch(Exception e){
            e.printStackTrace();
        }
        imageRepository.save(newImage);
    }

}
