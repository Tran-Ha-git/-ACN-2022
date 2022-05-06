package com.web.dacn.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.web.dacn.entity.book.Book;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
	Optional<Book> findOneBySlug(String slug);
}
