package com.example.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ResetPwdDto {

	private String customerName;
	private String email;
	private String oldPwd;
	private String newPwd;
	private String confirmPwd;

}
