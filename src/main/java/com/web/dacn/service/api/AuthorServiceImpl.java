package com.web.dacn.service.api;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.web.dacn.repository.AuthorRepository;

@Service
public class AuthorServiceImpl implements AuthorService {

	@Autowired
	private AuthorRepository authorRepository;

	@Override
	public void deleteAllById(Long[] ids) throws RuntimeException {
		Iterable<Long> iter = Arrays.asList(ids);
		authorRepository.deleteAllById(iter);
	}
}
