package com.example.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.dto.ProductCategoryDto;
import com.example.dto.ProductDto;
import com.example.entities.Product;
import com.example.mapper.ProductCategoryMapper;
import com.example.mapper.ProductMapper;
import com.example.repo.ProductCategoryRepo;
import com.example.repo.ProductRepo;

@Service
public class ProductServiceImpl implements ProductService {

	@Autowired
	private ProductRepo productRepo;

	@Autowired
	private ProductCategoryRepo categoryRepo;

	@Override
	public List<ProductCategoryDto> findAllCategories() {

		/*
		 * List<ProductCategoryDto> dtoList = new ArrayList<>();
		 * 
		 * List<ProductCategory> categories = categoryRepo.findAll();
		 * 
		 * for (ProductCategory category : categories) { ProductCategoryDto dto =
		 * ProductCategoryMapper.convertToDto(category); dtoList.add(dto); }
		 * 
		 * return dtoList;
		 */
		
		return categoryRepo.findAll()
				           .stream()
				           .map(ProductCategoryMapper::convertToDto)
				           .collect(Collectors.toList());
	}

	@Override
	public List<ProductDto> findProductsByCategoryId(Long categoryId) {
		
		return productRepo.findByCategoryId(categoryId)
				          .stream()
				          .map(ProductMapper::convertToDto)
				          .collect(Collectors.toList());
	}

	@Override
	public ProductDto findByProductId(Long productId) {

		/*
		 * Optional<Product> byId = productRepo.findById(productId);
		 * 
		 * if (byId.isPresent()) { Product product = byId.get(); return
		 * ProductMapper.convertToDto(product); } return null;
		 */
		
		return productRepo.findById(productId)
				          .map(ProductMapper::convertToDto)
				          .orElse(null);
	}

	@Override
	public List<ProductDto> findByProductName(String productName) {
		
		return productRepo.findByNameContaining(productName)
		           .stream()
		           .map(ProductMapper::convertToDto)
		           .collect(Collectors.toList());
	}

}
