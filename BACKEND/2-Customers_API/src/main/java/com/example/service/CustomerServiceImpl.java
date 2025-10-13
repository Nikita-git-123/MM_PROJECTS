package com.example.service;

import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.dto.CustomerDto;
import com.example.dto.ResetPwdDto;
import com.example.entities.Customer;
import com.example.mapper.CustomerMapper;
import com.example.repo.CustomerRepo;
import com.example.response.AuthResponse;

@Service
public class CustomerServiceImpl implements CustomerService {

	@Autowired
	private CustomerRepo customerRepo;

	@Autowired
	private EmailService emailService;

	@Autowired
	private BCryptPasswordEncoder pwdEncoder;

	@Autowired
	private AuthenticationManager authManager;

	Random rnd = new Random();

	@Override
	public boolean isEmailUnique(String email) {
		return null == customerRepo.findByEmail(email);
	}

	@Override
	public boolean register(CustomerDto CustomerDto) {

		String orgPwd = getRandomPwd();
		String encodedPwd = pwdEncoder.encode(orgPwd);

		Customer convertToEntity = CustomerMapper.convertToEntity(CustomerDto);

		convertToEntity.setPassword(encodedPwd);
		convertToEntity.setPwdUpdated("NO");

		Customer savedUser = customerRepo.save(convertToEntity);

		if (savedUser.getCustomerId() != null) {
			String subject = "Your Registration is successfull...";
			String body = "Your Account Password : " + orgPwd;
			return emailService.sendEmail(CustomerDto.getEmail(), subject, body);
		}

		return false;
	}

	@Override
	public AuthResponse login(CustomerDto customerDto) {

		AuthResponse response = new AuthResponse();

		UsernamePasswordAuthenticationToken authToken = 
				new UsernamePasswordAuthenticationToken(customerDto.getEmail(), customerDto.getPassword());
		
		Authentication authenticate = authManager.authenticate(authToken);
		
		if(authenticate.isAuthenticated()) {
			
			Customer c = customerRepo.findByEmail(customerDto.getEmail());
			response.setCustomer(CustomerMapper.convertToDto(c));
			response.setToken("");
		}

		return response;
	}

	@Override
	public boolean resetPwd(ResetPwdDto resetPwdDto) {
		Customer c = customerRepo.findByEmail(resetPwdDto.getEmail());

		if (c != null) {
			String newPwd = resetPwdDto.getNewPwd();
			String encodedPwd = pwdEncoder.encode(newPwd);
			c.setPassword(encodedPwd);
			c.setPwdUpdated("YES");
			customerRepo.save(c);
			return true;
		}
		return false;
	}

	@Override
	public CustomerDto getCustomerByEmail(String email) {
		Customer c = customerRepo.findByEmail(email);

		if (c != null) {
			return CustomerMapper.convertToDto(c);
		}
		return null;
	}


	private String getRandomPwd() {

		String SALTCHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
		StringBuilder pwd = new StringBuilder();
		while (pwd.length() < 5) {
			int index = (int) (rnd.nextFloat() * SALTCHARS.length());
			pwd.append(SALTCHARS.charAt(index));
		}
		String saltStr = pwd.toString();
		return saltStr;
	}

	@Override
	public boolean forgotPwd(String email) {
		
		Customer c = customerRepo.findByEmail(email);
		if(c != null) {
			String subject="Reset Password Request.";
			String body="This is temporary Email.";
			emailService.sendEmail(email, subject, body);
			return true;
		}
		
		return false;
	}

}
