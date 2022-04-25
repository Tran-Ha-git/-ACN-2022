package com.web.dacn.service.demo.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.web.dacn.dto.user.UserDto;
import com.web.dacn.entity.user.User;
import com.web.dacn.repository.UserRepository;
import com.web.dacn.service.demo.IDemoService;

@Service
public class DemoService implements IDemoService{
	@Autowired
	UserRepository userRepository;

	@Override
	public UserDto getDemo() {
		User user = userRepository.findById(1L).orElse(null);
				
		UserDto dto = new UserDto();
        if(user!=null) {
            dto.setUsername(user.getUsername());
            return dto;
        }
		return null;
	}

}
