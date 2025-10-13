package com.example.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.entity.CountryEntity;

public interface CountryRepo extends JpaRepository<CountryEntity, Integer>{

}
