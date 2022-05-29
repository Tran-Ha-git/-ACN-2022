package com.web.dacn.service.admin;

import java.util.List;

import org.springframework.data.domain.Page;

import com.web.dacn.dto.user.AuthorDTO;
import com.web.dacn.entity.book.Book;
import com.web.dacn.entity.user.Author;

public interface AuthorService {
	Page<AuthorDTO> getListAuthor(String search, String sortStr, int page);
	AuthorDTO findById(Long id);
}
