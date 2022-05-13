package com.web.dacn.service.client;

import java.util.List;

import com.web.dacn.dto.book.BookDTO;
import com.web.dacn.dto.quote.QuoteDto;

public interface HomeService {
	List<BookDTO> getNewBooks();
	List<BookDTO> getMangaBookByTopView();
	List<BookDTO> getAudioBookByTopView();
	List<BookDTO> getEbookBookByTopView();
	List<QuoteDto> getQuoteByTopView();
	List<BookDTO> getSuggestBook();
}
