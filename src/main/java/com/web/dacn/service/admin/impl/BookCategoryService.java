package com.web.dacn.service.admin.impl;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.FluentQuery.FetchableFluentQuery;
import org.springframework.stereotype.Service;

import com.web.dacn.entity.book.BookCategory;
import com.web.dacn.entity.quote.QuoteCategory;
import com.web.dacn.repository.BookCategoryRepository;
import com.web.dacn.service.admin.IBookCategoryService;

@Service
public class BookCategoryService implements IBookCategoryService{
	
	@Autowired
	private BookCategoryRepository bookCategoryRepository;


	@Override
	public List<BookCategory> findBySlug(String slug) {
		return bookCategoryRepository.findBySlug(slug);
	}

	@Override
	public List<BookCategory> findByParentBookCategoryIsNull() {
		return bookCategoryRepository.findByParentBookCategoryIsNull();
	}

	@Override
	public BookCategory findById(int id) {
		return bookCategoryRepository.findById(id);
	}

	@Override
	public List<BookCategory> findByName(String name) {
		return bookCategoryRepository.findByName(name);
	}

	@Override
	public Page<BookCategory> findByNameContaining(String name, Pageable pageable) {
		return bookCategoryRepository.findByNameContaining(name, pageable);
	}

	@Override
	public <S extends BookCategory> S save(S entity) {
		return bookCategoryRepository.save(entity);
	}

	@Override
	public <S extends BookCategory> Optional<S> findOne(Example<S> example) {
		return bookCategoryRepository.findOne(example);
	}

	@Override
	public List<BookCategory> findAll() {
		return bookCategoryRepository.findAll();
	}

	@Override
	public Page<BookCategory> findAll(Pageable pageable) {
		return bookCategoryRepository.findAll(pageable);
	}

	@Override
	public List<BookCategory> findAll(Sort sort) {
		return bookCategoryRepository.findAll(sort);
	}

	@Override
	public List<BookCategory> findAllById(Iterable<Long> ids) {
		return bookCategoryRepository.findAllById(ids);
	}

	@Override
	public Optional<BookCategory> findById(Long id) {
		return bookCategoryRepository.findById(id);
	}

	@Override
	public <S extends BookCategory> List<S> saveAll(Iterable<S> entities) {
		return bookCategoryRepository.saveAll(entities);
	}

	@Override
	public void flush() {
		bookCategoryRepository.flush();
	}

	@Override
	public <S extends BookCategory> S saveAndFlush(S entity) {
		return bookCategoryRepository.saveAndFlush(entity);
	}

	@Override
	public boolean existsById(Long id) {
		return bookCategoryRepository.existsById(id);
	}

	@Override
	public <S extends BookCategory> List<S> saveAllAndFlush(Iterable<S> entities) {
		return bookCategoryRepository.saveAllAndFlush(entities);
	}

	@Override
	public <S extends BookCategory> Page<S> findAll(Example<S> example, Pageable pageable) {
		return bookCategoryRepository.findAll(example, pageable);
	}

	@Override
	public void deleteInBatch(Iterable<BookCategory> entities) {
		bookCategoryRepository.deleteInBatch(entities);
	}

	@Override
	public <S extends BookCategory> long count(Example<S> example) {
		return bookCategoryRepository.count(example);
	}

	@Override
	public void deleteAllInBatch(Iterable<BookCategory> entities) {
		bookCategoryRepository.deleteAllInBatch(entities);
	}

	@Override
	public long count() {
		return bookCategoryRepository.count();
	}

	@Override
	public <S extends BookCategory> boolean exists(Example<S> example) {
		return bookCategoryRepository.exists(example);
	}

	@Override
	public void deleteById(Long id) {
		bookCategoryRepository.deleteById(id);
	}

	@Override
	public void deleteAllByIdInBatch(Iterable<Long> ids) {
		bookCategoryRepository.deleteAllByIdInBatch(ids);
	}

	@Override
	public void delete(BookCategory entity) {
		bookCategoryRepository.delete(entity);
	}

	@Override
	public <S extends BookCategory, R> R findBy(Example<S> example,
			Function<FetchableFluentQuery<S>, R> queryFunction) {
		return bookCategoryRepository.findBy(example, queryFunction);
	}

	@Override
	public void deleteAllById(Iterable<? extends Long> ids) {
		bookCategoryRepository.deleteAllById(ids);
	}

	@Override
	public void deleteAllInBatch() {
		bookCategoryRepository.deleteAllInBatch();
	}

	@Override
	public BookCategory getOne(Long id) {
		return bookCategoryRepository.getOne(id);
	}

	@Override
	public void deleteAll(Iterable<? extends BookCategory> entities) {
		bookCategoryRepository.deleteAll(entities);
	}

	@Override
	public void deleteAll() {
		bookCategoryRepository.deleteAll();
	}

	@Override
	public BookCategory getById(Long id) {
		return bookCategoryRepository.getById(id);
	}

	@Override
	public <S extends BookCategory> List<S> findAll(Example<S> example) {
		return bookCategoryRepository.findAll(example);
	}

	@Override
	public <S extends BookCategory> List<S> findAll(Example<S> example, Sort sort) {
		return bookCategoryRepository.findAll(example, sort);
	}
	
	
}
