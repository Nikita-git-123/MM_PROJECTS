package com.example.response;

import com.example.dto.CustomerDto;

import lombok.Data;

@Data
public class AuthResponse {
	
	private CustomerDto customer;
	private String token;

}
