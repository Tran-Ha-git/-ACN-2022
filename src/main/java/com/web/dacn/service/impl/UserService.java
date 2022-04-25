package com.web.dacn.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.web.dacn.dto.UserDto;
import com.web.dacn.entity.User;
import com.web.dacn.repository.UserRepository;
import com.web.dacn.service.IUserService;
import com.web.dacn.utils.UserConverter;

@Service
public class UserService implements IUserService{
	@Autowired
    private UserRepository userRepository;
	
	@Autowired
    private UserConverter userConverter;
	
	@Override
	public List<UserDto> findUsernameAndStatus(String username, int status) {
		List<User> users = new ArrayList<>();
		List<UserDto> results = new ArrayList<>();
		
		users = userRepository.findByUnameAndStatus(username, status);
		
		if(users.size()==0) return null;
		
		for (User user : users) {
			UserDto result = userConverter.convertToDto(user);
			results.add(result);
		}
		
		return results;
	}

	@Override
	public boolean addUser(UserDto user) {
		return false;
	}

}
