package com.web.dacn.service.auth;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.FluentQuery.FetchableFluentQuery;

import com.web.dacn.entity.user.UserRole;

public interface UserRoleService {

	<S extends UserRole> List<S> findAll(Example<S> example, Sort sort);

	<S extends UserRole> List<S> findAll(Example<S> example);

	UserRole getById(Long id);

	void deleteAll();

	void deleteAll(Iterable<? extends UserRole> entities);

	UserRole getOne(Long id);

	void deleteAllInBatch();

	void deleteAllById(Iterable<? extends Long> ids);

	<S extends UserRole, R> R findBy(Example<S> example, Function<FetchableFluentQuery<S>, R> queryFunction);

	void delete(UserRole entity);

	void deleteAllByIdInBatch(Iterable<Long> ids);

	void deleteById(Long id);

	<S extends UserRole> boolean exists(Example<S> example);

	long count();

	void deleteAllInBatch(Iterable<UserRole> entities);

	<S extends UserRole> long count(Example<S> example);

	void deleteInBatch(Iterable<UserRole> entities);

	<S extends UserRole> Page<S> findAll(Example<S> example, Pageable pageable);

	<S extends UserRole> List<S> saveAllAndFlush(Iterable<S> entities);

	boolean existsById(Long id);

	<S extends UserRole> S saveAndFlush(S entity);

	void flush();

	<S extends UserRole> List<S> saveAll(Iterable<S> entities);

	Optional<UserRole> findById(Long id);

	List<UserRole> findAllById(Iterable<Long> ids);

	List<UserRole> findAll(Sort sort);

	Page<UserRole> findAll(Pageable pageable);

	List<UserRole> findAll();

	<S extends UserRole> Optional<S> findOne(Example<S> example);

	<S extends UserRole> S save(S entity);

}
