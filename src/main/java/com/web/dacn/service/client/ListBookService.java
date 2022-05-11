package com.web.dacn.service.client;

import java.util.List;

import org.springframework.data.domain.Page;

import com.web.dacn.dto.book.BookCategoryDTO;
import com.web.dacn.dto.book.BookDTO;
import com.web.dacn.dto.book.Book_BookCategoryDTO;
import com.web.dacn.entity.book.Book;
import com.web.dacn.entity.book.Book_BookCategory;

public interface ListBookService {
	
	Page<BookDTO> getPageBook(String type, String search, String sort, int page, Long categoryId);
	
	List<BookDTO> getTopAudioBooks();
	
	List<BookDTO> getTopOnlineBooks();
	
	List<BookCategoryDTO> getBookCategories();
}
