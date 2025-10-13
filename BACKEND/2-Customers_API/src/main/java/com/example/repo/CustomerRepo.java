package com.example.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.dto.CustomerDto;
import com.example.entities.Customer;

public interface CustomerRepo extends JpaRepository<Customer, Long>{
	
	public Customer findByEmail(String email);
	
//	public Customer findByEmailAndPassword(CustomerDto customerDto);

}
