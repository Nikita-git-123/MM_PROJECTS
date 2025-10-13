package com.example.service;

import java.util.List;

import com.example.dto.ProductCategoryDto;
import com.example.dto.ProductDto;

public interface ProductService {

	public List<ProductCategoryDto> findAllCategories();

	public List<ProductDto> findProductsByCategoryId(Long categoryId);

	public List<ProductDto> findByProductName(String productName);
	
	public ProductDto findByProductId(Long productId);

}
