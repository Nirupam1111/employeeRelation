package com.nirupam.modelMapper.converter;

import com.nirupam.modelMapper.dto.AddressDto;
import com.nirupam.modelMapper.dto.AddressResponseDto;
import com.nirupam.modelMapper.model.Address;

public interface AddressConverter {
    Address AddressDtoToAddress(AddressDto addressDto);
    AddressResponseDto AddressToAddressResponseDto(Address address);
}
