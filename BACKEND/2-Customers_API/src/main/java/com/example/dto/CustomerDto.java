package com.example.dto;

import java.util.Date;

import lombok.Data;

@Data
public class CustomerDto {
	
	private Integer customerId;

	private String customerName;

	private String email;

	private String password;
	
	private String pwdUpdated;

	private String phoneNo;

	private Date dateCreated;

	private Date lastUpdated;

}
