package com.web.dacn.service.impl;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.web.dacn.dto.CustomUserDetail;
import com.web.dacn.dto.UserDto;
import com.web.dacn.entity.Role;
import com.web.dacn.entity.User;
import com.web.dacn.service.ICustomUserDetailService;
import com.web.dacn.service.IUserService;

@Service
public class CustomUserDetailService implements ICustomUserDetailService{
	@Autowired
    private IUserService userService;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		List<UserDto> users= userService.findUsernameAndStatus(username, 1);
		if(users.isEmpty())
			throw new UsernameNotFoundException("Username not found");
		UserDto user = users.get(0);
		
		Set<GrantedAuthority> authorities = new HashSet<>();
		
		for (Role role : user.getRoles()) {
			authorities.add(new SimpleGrantedAuthority("ROLE_"+role.getName()));
		}
		
		CustomUserDetail result = new CustomUserDetail(username, user.getPassword(), true, true, true, true, authorities);
		result.setId(user.getId());
		result.setFullName(user.getFullname());
		
		return result;
	}

}
