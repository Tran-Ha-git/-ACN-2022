package com.web.dacn.repository;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.web.dacn.entity.user.Author;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Long> {
	Author findById(long id);
	Page<Author> findByFullnameContainingAndStatus(String fullname, Integer status, Pageable page);
	Author findByFullname(String name);
	Optional<Author> findBySlug(String slug);
	boolean existsBySlug(String slug);
	boolean existsByIdNotAndSlug(Long id, String slug);
}
