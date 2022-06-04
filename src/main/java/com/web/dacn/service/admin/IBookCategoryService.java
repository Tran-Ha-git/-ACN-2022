package com.web.dacn.service.admin;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.FluentQuery.FetchableFluentQuery;

import com.web.dacn.entity.book.BookCategory;
import com.web.dacn.entity.quote.QuoteCategory;

public interface IBookCategoryService {

	<S extends BookCategory> List<S> findAll(Example<S> example, Sort sort);

	<S extends BookCategory> List<S> findAll(Example<S> example);

	BookCategory getById(Long id);

	void deleteAll();

	void deleteAll(Iterable<? extends BookCategory> entities);

	BookCategory getOne(Long id);

	void deleteAllInBatch();

	void deleteAllById(Iterable<? extends Long> ids);

	<S extends BookCategory, R> R findBy(Example<S> example, Function<FetchableFluentQuery<S>, R> queryFunction);

	void delete(BookCategory entity);

	void deleteAllByIdInBatch(Iterable<Long> ids);

	void deleteById(Long id);

	<S extends BookCategory> boolean exists(Example<S> example);

	long count();

	void deleteAllInBatch(Iterable<BookCategory> entities);

	<S extends BookCategory> long count(Example<S> example);

	void deleteInBatch(Iterable<BookCategory> entities);

	<S extends BookCategory> Page<S> findAll(Example<S> example, Pageable pageable);

	<S extends BookCategory> List<S> saveAllAndFlush(Iterable<S> entities);

	boolean existsById(Long id);

	<S extends BookCategory> S saveAndFlush(S entity);

	void flush();

	<S extends BookCategory> List<S> saveAll(Iterable<S> entities);

	Optional<BookCategory> findById(Long id);

	List<BookCategory> findAllById(Iterable<Long> ids);

	List<BookCategory> findAll(Sort sort);

	Page<BookCategory> findAll(Pageable pageable);

	List<BookCategory> findAll();

	<S extends BookCategory> Optional<S> findOne(Example<S> example);

	<S extends BookCategory> S save(S entity);

	Page<BookCategory> findByNameContaining(String name, Pageable pageable);

	List<BookCategory> findByName(String name);

	BookCategory findById(int id);

	List<BookCategory> findByParentBookCategoryIsNull();

	List<BookCategory> findBySlug(String slug);

}
