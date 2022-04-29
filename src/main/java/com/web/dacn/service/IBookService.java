package com.web.dacn.service;

import java.util.List;

import com.web.dacn.dto.BookDto;

public interface IBookService {
	
	BookDto getBookById(Long id);
	
	BookDto getBookBySlug(String slug);
	
	List<BookDto> getAll(Integer pageNo, Integer pageSize, String sortBy);

	List<BookDto> getAllBookAudio(Integer pageNo, Integer pageSize, String sortBy);
}
