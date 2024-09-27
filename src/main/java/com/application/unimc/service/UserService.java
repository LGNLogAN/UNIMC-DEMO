package com.application.unimc.service;

import com.application.unimc.dto.UserDTO;

public interface UserService {
	public void signup(UserDTO userDTO);
	public void simpleSignup();
	public boolean login(UserDTO userDTO);
	public boolean isEmailExists(String uniEmail);
}
