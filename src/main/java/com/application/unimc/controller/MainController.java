package com.application.unimc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class MainController {
	@GetMapping("/announce")
	public String announce() {
		return "pages/announce";
	}
	
	@GetMapping("/rule")
	public String rule() {
		return "pages/rule";
	}
	
	@GetMapping("/live")
	public String live() {
		return "pages/live";
	}
	
	@GetMapping("/FAQ")
	public String FAQ() {
		return "pages/FAQ";
	}
	
	@GetMapping("/login")
	public String login() {
		return "pages/login";
	}
	
	@GetMapping("/register")
	public String register() {
		return "pages/register/register";
	}
	@GetMapping("/mypage")
	public String mypage() {
		return "pages/mypage";
	}
	@GetMapping("/signup")
	public String signup() {
		return "pages/register/signup";
	}
	@GetMapping("/simplesignup")
	public String simpleSignup() {
		return "pages/register/simpleSignup";
	}
	
}
