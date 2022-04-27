package com.web.dacn.service;

import java.util.List;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.web.dacn.dto.UserDto;
import com.web.dacn.dto.UserRegisterDto;
import com.web.dacn.entity.User;

public interface IUserService  {
	List<UserDto> findUsernameAndStatus(String username, int status);

	User addUser(UserDto user);

	User findByUsername(String username);

	User register(UserRegisterDto user);
}
