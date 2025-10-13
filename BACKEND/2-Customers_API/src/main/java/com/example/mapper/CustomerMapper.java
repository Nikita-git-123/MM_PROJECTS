package com.example.mapper;

import org.modelmapper.ModelMapper;

import com.example.dto.CustomerDto;
import com.example.entities.Customer;

public class CustomerMapper {

	private static final ModelMapper modelMapper = new ModelMapper();

	public static CustomerDto convertToDto(Customer entity) {
		return modelMapper.map(entity, CustomerDto.class);
	}

	public static Customer convertToEntity(CustomerDto customerDto) {
		return modelMapper.map(customerDto, Customer.class);
	}

}
