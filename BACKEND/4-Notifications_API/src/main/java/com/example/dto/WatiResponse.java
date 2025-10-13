package com.example.dto;

import java.util.List;

import lombok.Data;

@Data
public class WatiResponse {
	
	private String result;
	private String phone_number;
	private List<WatiParameters> parameters;
	private boolean validWhatsAppNumber;
	private String name;
	private String orderNumber;
	

}
