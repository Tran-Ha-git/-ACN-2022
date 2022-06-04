package com.web.dacn.service.client;

import org.springframework.data.domain.Page;

import com.web.dacn.dto.book.BookDTO;
import com.web.dacn.dto.book.BookMarkDTO;

public interface BookmarkService {
	Boolean checkAudioMark(String bookSlug, Long id);

	Boolean addAudioMark(String bookSlug, Long id);

	Boolean deleteAudioMark(String bookSlug, Long id);

	Boolean addOnlineMark(String bookSlug, String chapter);

	Boolean checkOnlineMark(String bookSlug, String chapter);

	Boolean deleteOnlineMark(String bookSlug, String chapter);

	Boolean deleteOnlineMarkById(String bookSlug, Long id);
	
	Boolean addPdfMark(String bookSlug, Long id);

	Boolean checkPdfMark(String bookSlug, Long id);

	Boolean deletePdfMarkById(String bookSlug, Long id);
	
	Page<BookMarkDTO> getBookmark(String type, String search, int page);


}
