
package com.web.dacn.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.web.dacn.entity.book.BookCategory;

@Repository
public interface BookCategoryRepository extends JpaRepository<BookCategory, Long>{
	BookCategory findById(int id);
	List<BookCategory> findByName(String name);
	Page<BookCategory> findByNameContaining(String name, Pageable pageable);
	List<BookCategory> findByParentBookCategoryIsNull();
	List<BookCategory> findBySlug(String slug);
}

