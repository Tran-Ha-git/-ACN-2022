package com.web.dacn.service.impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.web.dacn.dto.CustomUserDetail;
import com.web.dacn.dto.UserDto;
import com.web.dacn.entity.User;
import com.web.dacn.repository.UserRepository;
import com.web.dacn.service.ICustomUserDetailService;
import com.web.dacn.utils.UserConverter;

@Service
public class CustomUserDetailService implements ICustomUserDetailService {
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private UserConverter userConverter;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		List<User> entities = userRepository.findByUnameAndStatus(username, 1);
		List<UserDto> users = new ArrayList<UserDto>();
		for (User user : entities) {
			users.add(userConverter.convertToDto(user));
		}
		if (users.isEmpty())
			throw new UsernameNotFoundException("Username not found");
		UserDto user = users.get(0);

		Set<GrantedAuthority> authorities = new HashSet<>();
		List<String> roles = user.getRole();
		if (roles != null && roles.size() > 0) {
			for (int i = 0; i < roles.size(); i++) {
				authorities.add(new SimpleGrantedAuthority("ROLE_" + roles.get(i)));
			}
		}

		CustomUserDetail result = new CustomUserDetail(username, user.getPassword(), true, true, true, true,
				authorities);
		result.setId(user.getId());
		result.setFullName(user.getFullname());

		return result;
	}

}
