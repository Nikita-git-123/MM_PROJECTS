package com.example.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.entities.Address;

public interface AddressRepo extends JpaRepository<Address, Long>{

}
