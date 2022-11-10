package com.nirupam.modelMapper.model;

import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;

@Entity
public class Image {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiModelProperty(name = "id",dataType = "int",example = "1")
    private int id;
    @Lob
    @ApiModelProperty(name = "profile image",dataType = "String",example = "abcd")
    private String profileImg;
    @ApiModelProperty(name = "name of image",dataType = "String",example = "xyz.jpg")
    private String name;
    @ApiModelProperty(name = "size of image",dataType = "String",example = "423120")
    private String size;
    @ApiModelProperty(name = "type of image",dataType = "String",example = "jpg/jpeg")
    private String type;
    @OneToOne
    @JoinColumn(name = "employee_id")
    private Employee employee;

    public Image() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getProfileImg() {
        return profileImg;
    }

    public void setProfileImg(String profileImg) {
        this.profileImg = profileImg;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

}
