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
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.web.dacn.dto.CustomUserDetail;
import com.web.dacn.dto.UserDto;
import com.web.dacn.dto.UserRegisterDto;
import com.web.dacn.entity.Role;
import com.web.dacn.entity.User;
import com.web.dacn.repository.RoleRepository;
import com.web.dacn.repository.UserRepository;
import com.web.dacn.service.IUserService;
import com.web.dacn.utils.UserConverter;

@Service
public class UserService implements IUserService {
	@Autowired
	private UserRepository userRepository;

	@Autowired
	private UserConverter userConverter;

	@Autowired
	private RoleRepository roleRepository;

	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	@Override
	public List<UserDto> findUsernameAndStatus(String username, int status) {
		List<User> users = new ArrayList<>();
		List<UserDto> results = new ArrayList<>();

		users = userRepository.findByUnameAndStatus(username, status);

		if (users.size() == 0)
			return null;

		for (User user : users) {
			UserDto result = userConverter.convertToDto(user);
			List<String> nameroles = new ArrayList<String>();
			List<Role> roles = user.getRoles();
			if (roles != null && roles.size() > 0) {
				for (Role role : user.getRoles()) {
					nameroles.add(role.getName());
				}
				result.setRole(nameroles);
			}

			results.add(result);
		}

		return results;
	}

	@Override
	public User addUser(UserDto user) {
		User entity = userConverter.convertToEntity(user);
		Role role = roleRepository.findById(1L).orElse(null);
		entity.getRoles().add(role);
		entity.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
		return userRepository.save(entity);
	}

	@Override
	public User findByUsername(String username) {
		return userRepository.findByUname(username);
	}

	@Override
	public User register(UserRegisterDto user) {
		User entity = userConverter.convertRegisterToEntity(user);
		entity.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
		entity.setStatus(1);
		Role role = roleRepository.findById(1L).orElse(null);
		entity.getRoles().add(role);
		return userRepository.save(entity);
	}

}
