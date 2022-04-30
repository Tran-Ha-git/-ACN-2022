package com.web.dacn.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.web.dacn.entity.user.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {

}
