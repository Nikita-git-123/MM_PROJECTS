package com.example.service;

import com.example.dto.CustomerDto;
import com.example.dto.ResetPwdDto;
import com.example.response.AuthResponse;

public interface CustomerService {

	public boolean isEmailUnique(String email);

	public boolean register(CustomerDto CustomerDto);
	
	public AuthResponse login(CustomerDto customerDto);

	public boolean resetPwd(ResetPwdDto resetPwdDto);
	
	public CustomerDto getCustomerByEmail(String email);
	
	public boolean forgotPwd(String email);

}
