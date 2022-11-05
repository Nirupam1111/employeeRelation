package com.nirupam.modelMapper.repository;

import com.nirupam.modelMapper.model.Image;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.transaction.Transactional;
import java.util.Optional;

public interface ImageRepository extends JpaRepository<Image, Integer> {
    Optional<Image> findByEmployeeId(int id);
    @Transactional
    void deleteByEmployeeId(int id);
}
