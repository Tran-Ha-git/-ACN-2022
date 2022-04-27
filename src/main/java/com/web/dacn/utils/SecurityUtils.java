package com.web.dacn.utils;

import java.util.ArrayList;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;

import com.web.dacn.dto.CustomUserDetail;

public class SecurityUtils {
	public static CustomUserDetail getPrincipal() {
		return (CustomUserDetail) (SecurityContextHolder.getContext()).getAuthentication().getPrincipal();
	}

	// access management
	public static List<String> getAuthorities() {
		List<String> results = new ArrayList<>();
		List<GrantedAuthority> authorities = (List<GrantedAuthority>) (SecurityContextHolder.getContext()
				.getAuthentication().getAuthorities());
		for (GrantedAuthority authority : authorities) {
			results.add(authority.getAuthority());
		}
		return results;
	}
}
