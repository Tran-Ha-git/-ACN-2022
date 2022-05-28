package com.web.dacn.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.web.dacn.entity.user.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
	public Optional<Role> findByName(String name);
	public Optional<Role> findByNameIgnoreCase(String name);
}
