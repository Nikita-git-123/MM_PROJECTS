package com.example.service;

import java.util.Map;

import com.example.dto.QuoteResponseDto;
import com.example.dto.ResetPwdDto;
import com.example.dto.UserDto;

public interface UserService {
	
	public UserDto login(String email, String pwd);
	
	public Map<Integer, String> getCountries();
	
	public Map<Integer, String> getStates(Integer countryId);
	
	public Map<Integer, String> getCities(Integer stateId);
	
	public boolean isEmailUnique(String email);
	
	public boolean register(UserDto userDto);
	
	public boolean resetPwd(ResetPwdDto resetPwdDto);
	
	public QuoteResponseDto getQuotation();

}
