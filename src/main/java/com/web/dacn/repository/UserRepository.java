package com.web.dacn.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.web.dacn.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {
	
}