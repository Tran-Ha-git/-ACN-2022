package com.web.dacn.service.admin;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import com.web.dacn.dto.book.BookDTO;
import com.web.dacn.entity.book.Book;
import com.web.dacn.entity.book.BookCategory;
import com.web.dacn.entity.user.Author;

@Service
public interface IBookService {

	Page<BookDTO> getBooksByPage(int page);

	List<String> getReadFromat(long bookId);

	Page<BookDTO> search(String bookName, String authorName, int page);

	Book saveBook(Book entity);

	BookDTO findById(long id);

	Author saveAuthor(Author author);

	boolean checkAuthor(Author a, List<Author> authors);

	Book getBookById(long id);

	Author findAuthorByFullName(String name);

	Author findAuthorById(long id);

	List<BookCategory> findCategoryByName(String name);

	BookCategory findCategoryById(long id);

	BookCategory saveCategory(BookCategory category);

	Book deleteBook(long id);
}
