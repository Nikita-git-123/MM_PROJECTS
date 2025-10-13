package com.example.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.dto.QuoteResponseDto;
import com.example.dto.ResetPwdDto;
import com.example.dto.UserDto;
import com.example.service.UserService;

@Controller
public class UserController {

	@Autowired
	private UserService userService;

	@GetMapping("/")
	public String index(Model model) {

		UserDto userDto = new UserDto();
		model.addAttribute("user", userDto);

		return "index";
	}

	@PostMapping("/login")
	public String login(@ModelAttribute("user") UserDto userDto, Model model) {

		UserDto login = userService.login(userDto.getEmail(), userDto.getPwd());

		if (login == null) {
			model.addAttribute("emsg", "Invalid Credentials");
			return "index";
		}

		if (login.getPwdUpdated().equals("YES")) {

			QuoteResponseDto quote = new QuoteResponseDto();
			model.addAttribute("quote", quote);
			return "dashboard";

		} else {

			ResetPwdDto resetPwd = new ResetPwdDto();
			resetPwd.setEmail(login.getEmail());
			model.addAttribute("resetPwd", resetPwd);
			return "resetPwd";
		}
	}

	@GetMapping("/register")
	public String register(Model model) {

		UserDto userDto = new UserDto();
		model.addAttribute("user", userDto);

		Map<Integer, String> countriesMap = userService.getCountries();
		model.addAttribute("countries", countriesMap);
		return "register";
	}

	@GetMapping("/states/{countryId}")
	@ResponseBody
	public Map<Integer, String> getStates(@PathVariable Integer countryId) {
		return userService.getStates(countryId);
	}

	@GetMapping("/cities/{stateId}")
	@ResponseBody
	public Map<Integer, String> getCities(@PathVariable Integer stateId) {
		return userService.getCities(stateId);
	}

	@PostMapping("/register")
	public String registerUser(@ModelAttribute("user") UserDto userDto, Model model) {

		boolean emailUnique = userService.isEmailUnique(userDto.getEmail());

		if (emailUnique) {

			boolean register = userService.register(userDto);

			if (register) {
				model.addAttribute("smsg", "Registration Successful..");
			} else {
				model.addAttribute("emsg", "Registration Failure");
			}

		} else {
			model.addAttribute("emsg", "Duplicate Email Found");
		}

		Map<Integer, String> countriesMap = userService.getCountries();
		model.addAttribute("countries", countriesMap);

		return "register";
	}

	@PostMapping("/resetPwd")
	public String resetPwd(@ModelAttribute("resetPwd") ResetPwdDto resetPwdDto, Model model) {
		
		UserDto login = userService.login(resetPwdDto.getEmail(), resetPwdDto.getOldPwd());
		if(login == null) {
			model.addAttribute("emsg", "Old Pwd is incorrect...");
			return "resetPwd";
		}

		if (resetPwdDto.getNewPwd().equals(resetPwdDto.getConfirmPwd())) {
			userService.resetPwd(resetPwdDto);
			QuoteResponseDto quote = userService.getQuotation();
			model.addAttribute("quote", quote);
			return "dashboard";
		} else {
			model.addAttribute("emsg", "New Pwd & Confirm Pwd Not Matching");
			return "resetPwd";
		}

	}

	@GetMapping("/getQuote")
	public String getNewQuote(Model model) {
		QuoteResponseDto quote = userService.getQuotation();
		model.addAttribute("quote", quote);
		return "dashboard";
	}

}
