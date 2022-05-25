package com.web.dacn.service.admin;

import org.springframework.data.domain.Page;

import com.web.dacn.dto.user.AuthorDTO;

public interface ListAuthorService {
	Page<AuthorDTO> getListAuthor(String search, String sortStr, int page);
}
