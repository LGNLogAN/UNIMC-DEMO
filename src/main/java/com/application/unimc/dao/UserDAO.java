package com.application.unimc.dao;

import org.apache.ibatis.annotations.Mapper;

import com.application.unimc.dto.UserDTO;

@Mapper
public interface UserDAO {
	public void signup(UserDTO userDTO);
	public void simpleSignup();
	public UserDTO login(String userId);
}
