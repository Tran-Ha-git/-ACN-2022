package com.web.dacn.service.admin;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.FluentQuery.FetchableFluentQuery;

import com.web.dacn.dto.user.AuthorDTO;
import com.web.dacn.entity.book.Book;
import com.web.dacn.entity.user.Author;
import com.web.dacn.repository.AuthorRepository;

public interface AuthorService {
	Page<AuthorDTO> getListAuthor(String search, String sortStr, int page);
	<S extends Author> List<S> findAll(Example<S> example, Sort sort);
	<S extends Author> List<S> findAll(Example<S> example);
	Author getById(Long id);
	void deleteAll();
	void deleteAll(Iterable<? extends Author> entities);
	Author getOne(Long id);
	void deleteAllInBatch();
	void deleteAllById(Iterable<? extends Long> ids);
	<S extends Author, R> R findBy(Example<S> example, Function<FetchableFluentQuery<S>, R> queryFunction);
	void delete(Author entity);
	void deleteAllByIdInBatch(Iterable<Long> ids);
	void deleteById(Long id);
	<S extends Author> boolean exists(Example<S> example);
	long count();
	void deleteAllInBatch(Iterable<Author> entities);
	<S extends Author> long count(Example<S> example);
	void deleteInBatch(Iterable<Author> entities);
	<S extends Author> Page<S> findAll(Example<S> example, Pageable pageable);
	<S extends Author> List<S> saveAllAndFlush(Iterable<S> entities);
	boolean existsById(Long id);
	<S extends Author> S saveAndFlush(S entity);
	void flush();
	<S extends Author> List<S> saveAll(Iterable<S> entities);
	List<Author> findAllById(Iterable<Long> ids);
	List<Author> findAll(Sort sort);
	Page<Author> findAll(Pageable pageable);
	List<Author> findAll();
	<S extends Author> Optional<S> findOne(Example<S> example);
	<S extends Author> S save(S entity);
	boolean existsByIdNotAndSlug(Long id, String slug);
	boolean existsBySlug(String slug);
	Optional<Author> findBySlug(String slug);
	Author findByFullname(String name);
	Page<Author> findByFullnameContaining(String fullname, Pageable page);
	Author findById(long id);
	AuthorDTO findById(Long id);
}
