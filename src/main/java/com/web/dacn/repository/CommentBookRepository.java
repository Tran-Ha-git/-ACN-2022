package com.web.dacn.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.web.dacn.entity.book.CommentBook;

@Repository
public interface CommentBookRepository extends JpaRepository<CommentBook, Long> {
	Page<CommentBook> findByBookSlug(String slug, Pageable pageable);

	Page<CommentBook> findByBookSlugOrderByModTimeDesc(String slug, Pageable pageable);

	Page<CommentBook> findByBookSlugAndCommentBookBookIdIsNullOrderByModTimeDesc(String slug, Pageable pageable);

	List<CommentBook> findByCommentBookCommentBooksId(Long commentId);

	Page<CommentBook> findByBookSlugAndCommentBookBookIdIsNullAndStatusOrderByModTimeDesc(String slug,
			Pageable pageable, Integer status);
}
