package com.web.dacn.service.api.impl;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.web.dacn.dto.user.AuthorDTO;
import com.web.dacn.entity.user.Author;
import com.web.dacn.repository.AuthorRepository;
import com.web.dacn.service.api.AuthorAPIService;

@Service
public class AuthorAPIServiceImpl implements AuthorAPIService {

	@Autowired
	private AuthorRepository authorRepository;

	@Override
	public void deleteAllById(Long[] ids) throws RuntimeException {
		for(Long i : ids) {
			Optional<Author> optional = authorRepository.findById(i);
			if(optional.isPresent()) {
				Author author = optional.get();
				author.setStatus(0);
				authorRepository.save(author);
			}
		}
	}

	@Override
	public AuthorDTO saveAuthorDTO(AuthorDTO dto) throws RuntimeException {
		Optional<Author> optional = authorRepository.findBySlug(dto.getSlug());
		if(optional.isPresent()) throw new RuntimeException("Slug already exist");
		Author author = new Author();
		BeanUtils.copyProperties(dto, author);
		author.setModTime(new Date());
		authorRepository.save(author);
		BeanUtils.copyProperties(author, dto);
		return dto;
	}
	
	@Override
	public AuthorDTO updateAuthorDTO(AuthorDTO dto) throws RuntimeException {
		Author author = new Author();
		BeanUtils.copyProperties(dto, author);
		author.setModTime(new Date());
		authorRepository.save(author);
		BeanUtils.copyProperties(author, dto);
		return dto;
	}

	@Override
	public Optional<Author> findById(Long id) {
		return authorRepository.findById(id);
	}

	@Override
	public boolean isExistsBySlug(String slug) {
		return authorRepository.existsBySlug(slug);
	}

	@Override
	public boolean isExistsByIdNotAndSlug(Long id, String slug) {
		return authorRepository.existsByIdNotAndSlug(id, slug);
	}
	
	
}
