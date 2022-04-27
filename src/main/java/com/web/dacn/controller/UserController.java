package com.web.dacn.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.web.dacn.dto.UserRegisterDto;
import com.web.dacn.service.impl.UserService;

@Controller
@RequestMapping(path = "/user")
public class UserController {

	@Autowired
	private UserService userService;

	@PostMapping("/registration")
	public String registration(@ModelAttribute("userForm") UserRegisterDto userForm) {
		userService.register(userForm);
		return "authPage";
	}
}
