package com.application.unimc.service;

import com.application.unimc.dto.UserDTO;

public interface UserService {
	public String signup(UserDTO userDTO);
	public void simpleSignup(UserDTO userDTO);
	public boolean login(UserDTO userDTO);
	public boolean isEmailExists(String uniEmail);
	public boolean signUpValidation(UserDTO userDTO);
}
