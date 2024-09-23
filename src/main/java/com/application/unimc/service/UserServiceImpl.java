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
	private PasswordEncoder passwordEncoder;
	
	@Override
	public void signup(UserDTO userDTO) {
		
		userDTO.setPassword(passwordEncoder.encode(userDTO.getPassword()));
		
		userDAO.signup(userDTO);
		System.out.println("UserServiceImpl : " + userDTO);
	}
}
