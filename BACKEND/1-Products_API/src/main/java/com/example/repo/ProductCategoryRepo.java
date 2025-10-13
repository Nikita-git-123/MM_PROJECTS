package com.example.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.entities.ProductCategory;

public interface ProductCategoryRepo extends JpaRepository<ProductCategory, Long>{

}
