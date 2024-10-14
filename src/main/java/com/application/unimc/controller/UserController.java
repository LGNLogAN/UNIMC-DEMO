package com.application.unimc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.application.unimc.dto.UserDTO;
import com.application.unimc.service.UserService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
public class UserController {
	@Autowired
	private UserService userService;
	
	@Autowired
	private EmailAuthController emailAuthController;
	
	@PostMapping("/signup")
	public String signup(@ModelAttribute UserDTO userDTO , @RequestParam("code") String code) {
		System.out.println("UserController : " + userDTO);
		
		boolean isVerified = emailAuthController.verifyCode(userDTO.getUniEmail(), code);
		if(!isVerified) {
			return "redirect:/register";
		}
		userDTO.setIsVerified("y");
		userService.signup(userDTO);
		return "redirect:/login";
	}
	
	@PostMapping("/login")
	@ResponseBody
	public String login(@RequestBody UserDTO userDTO , HttpServletRequest request) {
		
		String isValidUser = "n";
		System.out.println("UserController{login} : " + userDTO.getUniEmail() + userDTO.getPassword());
		
		if(userService.login(userDTO)) {
			HttpSession session = request.getSession();
			session.setAttribute("uniEmail", userDTO.getUniEmail());
			
			isValidUser = "y";
		}
		
		return isValidUser;
	}
	
	@GetMapping("/signOut")
	public String signOut(HttpServletRequest request) {
		HttpSession session = request.getSession();
		session.invalidate();
		System.out.println("Session Out");
		return "redirect:/announce";
	}
	
	
}
