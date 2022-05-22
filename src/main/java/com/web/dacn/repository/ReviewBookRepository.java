package com.web.dacn.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

import com.web.dacn.entity.book.ReviewBook;

@Repository
public interface ReviewBookRepository extends JpaRepository<ReviewBook, Long> {
	List<ReviewBook> findByBookSlug(String slug);

	ReviewBook findTop1ByBookIdAndUserIdOrderByModTime(Long id, Long id2);

	ReviewBook findByBookIdAndUserId(Long id, Long id2);

	boolean existsByBook_SlugAndUserId(String slug, Long id);

	ReviewBook findTop1ByBookIdAndUserIdAndModTime(Long id, Long id2, Date modTime);
}
