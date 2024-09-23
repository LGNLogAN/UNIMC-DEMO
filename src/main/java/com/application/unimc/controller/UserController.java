package com.application.unimc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.application.unimc.dto.UserDTO;
import com.application.unimc.service.UserService;

@Controller
public class UserController {
	@Autowired
	private UserService userService;
	
	
	@PostMapping("/signup")
	public String signup(@ModelAttribute UserDTO userDTO) {
		userService.signup(userDTO);
		System.out.println("UserController : " + userDTO);
		return "redirect:/login";
	}
}
