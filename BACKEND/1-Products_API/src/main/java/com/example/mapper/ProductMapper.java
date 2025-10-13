package com.example.mapper;

import org.modelmapper.ModelMapper;

import com.example.dto.ProductDto;
import com.example.entities.Product;

public class ProductMapper {

	public static final ModelMapper modelMapper = new ModelMapper();

	public static ProductDto convertToDto(Product entity) {
		return modelMapper.map(entity, ProductDto.class);
	}

	public static Product convertToEntity(ProductDto productDto) {
		return modelMapper.map(productDto, Product.class);
	}

}
