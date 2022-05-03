package com.web.dacn.service.book;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.web.dacn.dto.book.BookDTO;

@Service
public interface IBookService {

	Page<BookDTO> getBooksByPage(int page);

	Pageable getBookEntitesByPage(int page);
//
//	List<String> getAuthorsOfBook(long id);
//
//	List<String> getCategoriesOfBook(long id);

	Page<BookDTO> search(String bookName, String authorName, int page);

//	void setBookCategoriesAndAuthors(List<BookDTO> books);

}
