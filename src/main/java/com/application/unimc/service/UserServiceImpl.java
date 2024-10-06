package com.application.unimc.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.application.unimc.dao.UserDAO;
import com.application.unimc.dto.UserDTO;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserDAO userDAO;
	
	@Autowired
	private UniversityDomainCheckService domainCheckService;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private RegexService regexService;
	
	
	@Override
	public boolean signUpValidation(UserDTO userDTO) {
		boolean isValidation = false;
		
		if(!regexService.emailMatchesPattern(userDTO.getUniEmail())) {
			isValidation = false;
		}else if(domainCheckService.univerysityNameCheck(userDTO.getUniEmail()) == null){
			isValidation = false;
		}else if(isEmailExists(userDTO.getUniEmail())){
			isValidation = false;
		}else if(!regexService.nameMatchesPattern(userDTO.getName())) {
			isValidation = false;
		}else if(!regexService.mcNameMatchesPattern(userDTO.getMcName())) {
			isValidation = false;
		}else if(userDTO.getMajor() == null){
			isValidation = false;
		}else if(userDTO.getCampus() == null){
			isValidation = false;
		}else if(!userDTO.getIsVerified().equals("y")){
			isValidation = false;
		}else {
			isValidation = true;
		}
		
		return isValidation;

	}
	
	@Override
	public String signup(UserDTO userDTO) {
		
		if (signUpValidation(userDTO)) {
			userDTO.setPassword(passwordEncoder.encode(userDTO.getPassword()));
			userDTO.setUniversity(domainCheckService.univerysityNameCheck(userDTO.getUniEmail()));
			
			userDAO.signup(userDTO);
			System.out.println("UserServiceImpl : " + userDTO + "\nResult : SignUp Success");
			return "SignUp Success";
		}else {
			System.out.println("UserServiceImpl : " + userDTO + "\nResult : SignUp Fail");
			return "SignUp Fail";
		}
		
	}

	@Override
	public void simpleSignup() {
		// TODO 이미지 텍스트화 모든 로그인 기능 구현 후 구현
	}

	@Override
	public boolean login(UserDTO userDTO) {
		
		UserDTO userValid = userDAO.login(userDTO.getUniEmail());
		//System.out.println(userDTO.getUniEmail()+userDTO.getPassword());
		if(userValid != null) {
			if(passwordEncoder.matches(userDTO.getPassword(),userValid.getPassword())) {
				return true;
			}
		}
		return false;
	}

	@Override
	public boolean isEmailExists(String uniEmail) {
		boolean isEmailExists = false;
		if(userDAO.isEmailExists(uniEmail) != null) {
			isEmailExists = true;
		}else {
			isEmailExists = false;
		}
		return isEmailExists;
	}		
			
}
