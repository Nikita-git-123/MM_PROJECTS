package com.example.service;

import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.example.dto.QuoteResponseDto;
import com.example.dto.ResetPwdDto;
import com.example.dto.UserDto;
import com.example.entity.CityEntity;
import com.example.entity.CountryEntity;
import com.example.entity.StateEntity;
import com.example.entity.UserEntity;
import com.example.repo.CityRepo;
import com.example.repo.CountryRepo;
import com.example.repo.StateRepo;
import com.example.repo.UserRepo;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepo userRepo;

	@Autowired
	private CountryRepo countryRepo;

	@Autowired
	private StateRepo stateRepo;

	@Autowired
	private CityRepo cityRepo;

	@Autowired
	private EmailService emailService;

	@Override
	public UserDto login(String email, String pwd) {
		UserEntity entity = userRepo.findByEmailAndPwd(email, pwd);
		if (entity != null) {
			UserDto dto = new UserDto();
			BeanUtils.copyProperties(entity, dto);
			return dto;
		}
		return null;
	}

	@Override
	public Map<Integer, String> getCountries() {
		List<CountryEntity> countries = countryRepo.findAll();
		Map<Integer, String> countryMap = countries.stream()
				.collect(Collectors.toMap(country -> country.getCountryId(), country -> country.getCountryName()));
		return countryMap;
	}

	@Override
	public Map<Integer, String> getStates(Integer countryId) {
		List<StateEntity> states = stateRepo.findByCountryCountryId(countryId);
		Map<Integer, String> stateMap = states.stream()
				.collect(Collectors.toMap(state -> state.getStateId(), state -> state.getStateName()));
		return stateMap;
	}

	@Override
	public Map<Integer, String> getCities(Integer stateId) {
		List<CityEntity> cities = cityRepo.findByStateStateId(stateId);
		Map<Integer, String> cityMap = cities.stream()
				.collect(Collectors.toMap(city -> city.getCityId(), city -> city.getCityName()));
		return cityMap;
	}

	@Override
	public boolean isEmailUnique(String email) {
		return null == userRepo.findByEmail(email);
	}

	@Override
	public boolean register(UserDto userDto) {

		String randomPwd = getRandomPwd();
		userDto.setPwd(randomPwd);
		userDto.setPwdUpdated("NO");

		UserEntity entity = new UserEntity();
		BeanUtils.copyProperties(userDto, entity);

		CountryEntity country = countryRepo.findById(userDto.getCountryId()).get();
		StateEntity state = stateRepo.findById(userDto.getStateId()).get();
		CityEntity city = cityRepo.findById(userDto.getCityId()).get();

		entity.setCountry(country);
		entity.setState(state);
		entity.setCity(city);

		UserEntity savedUser = userRepo.save(entity);

		if (savedUser != null) {
			String subject = "Your Registration is successfull...";
			String body = "Your Account Password : " + userDto.getPwd();
			emailService.sendEmail(userDto.getEmail(), subject, body);
		}

		return savedUser != null;
	}

	@Override
	public boolean resetPwd(ResetPwdDto resetPwdDto) {
		UserEntity entity = userRepo.findByEmail(resetPwdDto.getEmail());
		entity.setPwd(resetPwdDto.getNewPwd());
		entity.setPwdUpdated("YES");
		UserEntity save = userRepo.save(entity);
		return save != null;
	}

	@Override
	public QuoteResponseDto getQuotation() {

		String ApiUrl = "https://dummyjson.com/quotes/random";
		RestTemplate rt = new RestTemplate();
		ResponseEntity<QuoteResponseDto> responseDto = rt.getForEntity(ApiUrl, QuoteResponseDto.class);
		System.out.println(responseDto);
		return responseDto.getBody();
	}

	private String getRandomPwd() {

		String SALTCHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
		StringBuilder pwd = new StringBuilder();
		Random rnd = new Random();
		while (pwd.length() < 5) {
			int index = (int) (rnd.nextFloat() * SALTCHARS.length());
			pwd.append(SALTCHARS.charAt(index));
		}
		String saltStr = pwd.toString();
		return saltStr;
	}

}
