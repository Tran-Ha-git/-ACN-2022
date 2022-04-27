package com.web.dacn.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.web.dacn.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {

	List<User> findByUnameAndStatus(String username, int status);

	User findByUname(String username);

}