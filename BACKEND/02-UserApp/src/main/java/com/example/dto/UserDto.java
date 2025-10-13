package com.example.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class UserDto {
	
	private Integer userId;
	private String username;
	private String email;
	private String pwd;
	private String pwdUpdated;
	private Long phno;
	
	private Integer countryId;
	private Integer StateId;
	private Integer cityId;

}
