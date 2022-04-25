package com.web.dacn.service;

import java.util.List;

import com.web.dacn.dto.UserDto;

public interface IUserService  {
	List<UserDto> findUsernameAndStatus(String username,int status);
	boolean addUser(UserDto user);
}
