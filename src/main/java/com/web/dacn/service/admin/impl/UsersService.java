package com.web.dacn.service.admin.impl;

import java.util.List;
import java.util.stream.Stream;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.web.dacn.dto.user.RoleDTO;
import com.web.dacn.dto.user.UserDto;
import com.web.dacn.entity.book.Book;
import com.web.dacn.entity.user.Role;
import com.web.dacn.entity.user.User;
import com.web.dacn.repository.RoleRepository;
import com.web.dacn.repository.UserRepository;
import com.web.dacn.service.admin.IUserService;
import com.web.dacn.utils.Converter;

@Service
public class UsersService implements IUserService {
	static int size = 20;
	@Autowired
	private UserRepository userRepository;

	@Autowired
	private RoleRepository roleRepository;

	@Autowired
	private Converter converter;

	public Pageable getUserEntitesByPage(int page) {
		Pageable pageable = PageRequest.of(page, size);
		return pageable;
	}

	@Override
	public Page<UserDto> getUsersByPage(int page, long role_id) {
		Page<User> entities = userRepository.findAll(getUserEntitesByPage(page), role_id);
		Page<UserDto> users = entities.map(entity -> {
			return converter.toDTO(entity);
		});
		return users;
	}

	@Override
	public List<Role> getRoles(long userId) {
		User user = userRepository.getById(userId);
		List<Role> roles = user.getRoles();
		return roles;
	}

	@Override
	public User deleteUser(long id) {

		User user = userRepository.getById(id);
		user.setStatus(0);
		userRepository.save(user);
		return user;

	}

	@Override
	public UserDto findUserById(long id) {
		return converter.toDTO(userRepository.getById(id));
	}

	@Override
	public User findById(long id) {
		return userRepository.getById(id);
	}

	@Override
	public Role findRoleById(long id) {
		return roleRepository.getById(id);
	}

	@Override
	public User save(User entity) {
		return userRepository.save(entity);
	}

	@Override
	public Page<UserDto> search(String input, int page) {

		Pageable pageable = PageRequest.of(page, size);
		Page<User> entities = null;
		entities = userRepository.findByUsernameOrEmailOrPhone(pageable, input.trim());

		Page<UserDto> users = entities.map(entity -> {
			return converter.toDTO(entity);
		});

		return users;
	}

}
