package com.web.dacn.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.web.dacn.entity.user.User;

public interface UserRepository extends JpaRepository<User, Long> {
	public Optional<User> findByUsernameIgnoreCase(String username);
	public Optional<User> findByEmail(String email);
}