package com.web.dacn.service.client;

import org.springframework.data.domain.Page;

import com.web.dacn.dto.book.BookDTO;

public interface FavoriteBookService {

	Page<BookDTO> getLoveBook(String search, int page);

}
