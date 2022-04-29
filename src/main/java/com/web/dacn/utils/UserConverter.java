package com.web.dacn.utils;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.web.dacn.dto.UserDto;
import com.web.dacn.dto.UserRegisterDto;
import com.web.dacn.entity.Role;
import com.web.dacn.entity.User;

@Service
public class UserConverter {

	public UserDto convertToDto(User user) {
		UserDto result = new UserDto();
		result.setId(user.getId());
		result.setUname(user.getUname());
		result.setPassword(user.getPassword());
		result.setFullname(user.getFullname());
		result.setEmail(user.getEmail());
		result.setBirthday(user.getBirthday());
		result.setAddress(user.getUname());
		result.setPhone(user.getUname());
		result.setStatus(user.getStatus());
		List<Role> roles = user.getRoles();
		List<String> roleNames = new ArrayList<String>();
		for (Role role : roles) {
			roleNames.add(role.getName());
		}
		result.setRole(roleNames);
		return result;
	}

	public User convertToEntity(UserDto dto) {
		User result = new User();
		result.setId(dto.getId());
		result.setUname(dto.getUname());
		result.setPassword(dto.getPassword());
		result.setFullname(dto.getFullname());
		result.setEmail(dto.getEmail());
		result.setBirthday(dto.getBirthday());
		result.setAddress(dto.getUname());
		result.setPhone(dto.getUname());
		result.setStatus(dto.getStatus());

		return result;
	}

	public User convertRegisterToEntity(UserRegisterDto register) {
		User result = new User();
		result.setUname(register.getName());
		result.setFullname(register.getFullname());
		result.setEmail(register.getEmail());
		result.setPassword(register.getPassword());
		return result;
	}
}
