package com.example.mapper;

import org.modelmapper.ModelMapper;

import com.example.dto.ProductCategoryDto;
import com.example.dto.ProductDto;
import com.example.entities.Product;
import com.example.entities.ProductCategory;

public class ProductCategoryMapper {

	public static final ModelMapper modelMapper = new ModelMapper();

	public static ProductCategoryDto convertToDto(ProductCategory entity) {
		return modelMapper.map(entity, ProductCategoryDto.class);
	}

	public static ProductCategory convertToEntity(ProductCategoryDto categoryDto) {
		return modelMapper.map(categoryDto, ProductCategory.class);
	}

}
