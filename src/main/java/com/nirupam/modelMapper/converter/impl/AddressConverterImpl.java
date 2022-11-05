package com.nirupam.modelMapper.converter.impl;

import com.nirupam.modelMapper.converter.AddressConverter;
import com.nirupam.modelMapper.dto.AddressDto;
import com.nirupam.modelMapper.dto.AddressResponseDto;
import com.nirupam.modelMapper.model.Address;
import org.modelmapper.ModelMapper;

public class AddressConverterImpl implements AddressConverter {
    public final ModelMapper modelMapper;

    public AddressConverterImpl(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @Override
    public Address AddressDtoToAddress(AddressDto addressDto) {
        return modelMapper.map(addressDto, Address.class);
    }

    @Override
    public AddressResponseDto AddressToAddressResponseDto(Address address) {
        return modelMapper.map(address, AddressResponseDto.class);
    }

}
