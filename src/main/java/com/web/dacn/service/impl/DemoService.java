package com.web.dacn.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.web.dacn.dto.UserDto;
import com.web.dacn.entity.User;
import com.web.dacn.repository.UserRepository;
import com.web.dacn.service.IDemoService;

@Service
public class DemoService implements IDemoService {
	@Autowired
	UserRepository userRepository;

	@Override
	public UserDto getDemo() {
		User user = userRepository.findById(1L).orElse(null);

		UserDto dto = new UserDto();
		if (user != null) {
			dto.setUname(user.getUname());
			return dto;
		}
		return null;
	}

}
