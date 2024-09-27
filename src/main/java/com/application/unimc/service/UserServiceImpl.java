package com.application.unimc.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.application.unimc.auth.UniAuth;
import com.application.unimc.dao.UserDAO;
import com.application.unimc.dto.UserDTO;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserDAO userDAO;
	
	@Autowired
	private UniAuth uniAuth;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Override
	public void signup(UserDTO userDTO) {
		
		userDTO.setPassword(passwordEncoder.encode(userDTO.getPassword()));
		userDTO.setUniversity(uniAuth.univerysityNameCheck(userDTO.getUniEmail()));
		
		userDAO.signup(userDTO);
		
		System.out.println("UserServiceImpl : " + userDTO);
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
