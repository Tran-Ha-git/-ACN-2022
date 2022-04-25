package com.web.dacn.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(path = "/a")
public class AuthController {
	@GetMapping("/login")
	public String loginPage(Model model) {
		return "authPage";
	}
	
	@GetMapping("/logout")
	public String logoutPage(Model model) {
		return "homePage";
	}
	
	@GetMapping("/auth")
	public String getAuthPage(Model model) {
		return "authPage";
	}
}
