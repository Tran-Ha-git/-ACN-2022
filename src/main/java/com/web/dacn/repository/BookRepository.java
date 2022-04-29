package com.web.dacn.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.web.dacn.entity.Book;

public interface BookRepository extends PagingAndSortingRepository<Book,Long>{

	Book findBySlug(String slug);

	Page<Book> findDistinctByAudiosNotNull(Pageable paging);
}
