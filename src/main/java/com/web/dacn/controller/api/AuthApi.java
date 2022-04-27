package com.web.dacn.controller.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.web.dacn.entity.User;
import com.web.dacn.service.impl.UserService;

@RestController
@RequestMapping(value = "/api/auth")
public class AuthApi {

	@Autowired
	private UserService userService;
	
	@GetMapping("validate-username")
	public String existUsername (@RequestParam String username) {
		if(userService.findByUsername(username) !=null) {
			return "Username existed!" ;
		}
		return "";
	}
	
	@GetMapping("get-id")
	public Long getId(String username) {
		User user = userService.findByUsername(username);
		if(user !=null) {
			return user.getId();
		}
		return null;
	}
}
