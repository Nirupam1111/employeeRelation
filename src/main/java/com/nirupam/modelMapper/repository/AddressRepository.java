package com.nirupam.modelMapper.repository;

import com.nirupam.modelMapper.model.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<Address, Integer> {
}
