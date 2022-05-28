package com.web.dacn.service.auth;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.FluentQuery.FetchableFluentQuery;

import com.web.dacn.entity.user.Role;

public interface RoleService {

	<S extends Role> List<S> findAll(Example<S> example, Sort sort);

	<S extends Role> List<S> findAll(Example<S> example);

	Role getById(Long id);

	void deleteAll();

	void deleteAll(Iterable<? extends Role> entities);

	Role getOne(Long id);

	void deleteAllInBatch();

	void deleteAllById(Iterable<? extends Long> ids);

	<S extends Role, R> R findBy(Example<S> example, Function<FetchableFluentQuery<S>, R> queryFunction);

	void delete(Role entity);

	void deleteAllByIdInBatch(Iterable<Long> ids);

	void deleteById(Long id);

	<S extends Role> boolean exists(Example<S> example);

	long count();

	void deleteAllInBatch(Iterable<Role> entities);

	<S extends Role> long count(Example<S> example);

	void deleteInBatch(Iterable<Role> entities);

	<S extends Role> Page<S> findAll(Example<S> example, Pageable pageable);

	<S extends Role> List<S> saveAllAndFlush(Iterable<S> entities);

	boolean existsById(Long id);

	<S extends Role> S saveAndFlush(S entity);

	void flush();

	<S extends Role> List<S> saveAll(Iterable<S> entities);

	Optional<Role> findById(Long id);

	List<Role> findAllById(Iterable<Long> ids);

	List<Role> findAll(Sort sort);

	Page<Role> findAll(Pageable pageable);

	List<Role> findAll();

	<S extends Role> Optional<S> findOne(Example<S> example);

	<S extends Role> S save(S entity);

	Optional<Role> findByName(String name);

	Optional<Role> findByNameIgnoreCase(String name);

}
