package com.example.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.entity.StateEntity;

public interface StateRepo extends JpaRepository<StateEntity, Integer>{
	
	public List<StateEntity> findByCountryCountryId(Integer countryId);

}
