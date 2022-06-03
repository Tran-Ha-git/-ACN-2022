package com.web.dacn.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.web.dacn.entity.book.Book;
import com.web.dacn.entity.book.FavoriteBook;

@Repository
public interface FavoriteBookRepository extends JpaRepository<FavoriteBook, Long>{

	Page<Book> findByUser_IdAndBook_NameContainingIgnoreCaseAndBook_Status(Long id, String search, Pageable pageable,
			int status);

	long deleteByUser_IdAndBook_Slug(Long id, String slug);

	boolean existsByUser_IdAndBook_Slug(Long id, String slug);

	long deleteByBookId(Long id);

}
