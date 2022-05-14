package com.web.dacn.service.admin;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import com.web.dacn.dto.book.BookDTO;
import com.web.dacn.entity.book.Book;

@Service
public interface IBookService {

	Page<BookDTO> getBooksByPage(int page);

	List<String> getReadFromat(long bookId);

	Page<BookDTO> search(String bookName, String authorName, int page);

	Book addBook(Book entity);

	BookDTO findById(long id);

	// Thay thế phương thức getBooks cũ
	Book getBookById(long id);
}
