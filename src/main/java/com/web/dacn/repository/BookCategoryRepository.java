package com.web.dacn.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.web.dacn.entity.AuthorEntity;
import com.web.dacn.entity.BookCategoryEntity;

@Repository
public interface BookCategoryRepository extends JpaRepository<BookCategoryEntity, Integer>{
	BookCategoryEntity findById(int id);
	

	 BookCategoryEntity findByName(String name);

}
