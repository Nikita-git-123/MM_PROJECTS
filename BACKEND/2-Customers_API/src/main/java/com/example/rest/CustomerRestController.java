package com.example.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.dto.CustomerDto;
import com.example.dto.ResetPwdDto;
import com.example.response.ApiResponse;
import com.example.response.AuthResponse;
import com.example.service.CustomerService;

@RestController
public class CustomerRestController {

	@Autowired
	private CustomerService customerService;

	@Autowired
	private BCryptPasswordEncoder pwdEncoder;

	@PostMapping("/register")
	public ResponseEntity<ApiResponse<String>> register(@RequestBody CustomerDto customerDto) {

		ApiResponse<String> response = new ApiResponse<>();

		boolean emailUnique = customerService.isEmailUnique(customerDto.getEmail());
		if (!emailUnique) {
			response.setStatus(400);
			response.setMessage("Failed");
			response.setData("Duplicate Email");
			return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
		}

		boolean register = customerService.register(customerDto);

		if (register) {
			response.setStatus(201);
			response.setMessage("Success");
			response.setData("Registration Success");
			return new ResponseEntity<>(response, HttpStatus.CREATED);
		} else {
			response.setStatus(500);
			response.setMessage("Failed");
			response.setData("Registration Failure");
			return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	@PostMapping("/login")
	public ResponseEntity<ApiResponse<AuthResponse>> login(@RequestBody CustomerDto customerDto) {

		ApiResponse<AuthResponse> response = new ApiResponse<>();

		AuthResponse authResponse = customerService.login(customerDto);

		if (authResponse != null) {
			response.setStatus(200);
			response.setMessage("Login Successful.");
			response.setData(authResponse);
			return new ResponseEntity<ApiResponse<AuthResponse>>(response, HttpStatus.OK);
		} else {
			response.setStatus(400);
			response.setMessage("Invalid Credentials");
			response.setData(null);
			return new ResponseEntity<ApiResponse<AuthResponse>>(response, HttpStatus.BAD_REQUEST);
		}
	}

	@PostMapping("/resetPwd")
	public ResponseEntity<ApiResponse<String>> resetPwd(@RequestBody ResetPwdDto resetPwdDto) {

		ApiResponse<String> response = new ApiResponse<>();

		CustomerDto customerDto = customerService.getCustomerByEmail(resetPwdDto.getEmail());
		if (!pwdEncoder.matches(resetPwdDto.getOldPwd(), customerDto.getPassword())) {
			response.setStatus(500);
			response.setMessage("Failed");
			response.setData("Current Password is incorrect.");
			return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
		}
		boolean status = customerService.resetPwd(resetPwdDto);

		if (status) {
			response.setStatus(200);
			response.setMessage("Success");
			response.setData("Password Updated.");
			return new ResponseEntity<>(response, HttpStatus.OK);
		} else {
			response.setStatus(500);
			response.setMessage("Failed");
			response.setData("Password Reset Failed.");
			return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}
	
	@GetMapping("/forgotPwd/{email}")
	public ResponseEntity<ApiResponse<String>> forgotPwd(@PathVariable String email) {

		ApiResponse<String> response = new ApiResponse<>();

		boolean status = customerService.forgotPwd(email);
		if (status) {
			response.setStatus(200);
			response.setMessage("Success");
			response.setData("Email sent to Reset Password .");
			return new ResponseEntity<>(response, HttpStatus.OK);
		} else {
			response.setStatus(500);
			response.setMessage("Failed");
			response.setData("No Account Found.");
			return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
		}

	}

}
