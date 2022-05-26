package com.web.dacn.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;
import com.web.dacn.entity.user.Author;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Long> {
	Author findById(long id);
	Page<Author> findByFullnameContaining(String fullname, Pageable page);
	Author findByFullname(String name);
}
