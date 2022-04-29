package com.web.dacn.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestParam;

import com.web.dacn.dto.BookDto;
import com.web.dacn.entity.Book;
import com.web.dacn.service.impl.BookService;

@Controller
public class BookController {
	@Autowired
	private BookService bookService;
	
	public List<BookDto> getAll( @RequestParam(defaultValue = "0") Integer pageNo, 
                        @RequestParam(defaultValue = "10") Integer pageSize,
                        @RequestParam(defaultValue = "id") String sortBy) {
		bookService.getAll(pageNo, pageSize, sortBy);
		return null;
	}
	
	public BookDto getById(Long id) {
		return bookService.getBookById(id);
	}
}
