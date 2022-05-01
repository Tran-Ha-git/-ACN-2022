package com.web.dacn.service.book.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.web.dacn.dto.BookDTO;
import com.web.dacn.entity.AudioEntity;
import com.web.dacn.entity.BookEntity;
import com.web.dacn.entity.OnlineEntity;
import com.web.dacn.entity.PdfEntity;
import com.web.dacn.repository.AudioRepository;
import com.web.dacn.repository.BookRepository;
import com.web.dacn.repository.OnlineRepository;
import com.web.dacn.repository.PdfRepository;
import com.web.dacn.service.book.IBookService;
import com.web.dacn.utils.Converter;

@Service
public class BookService implements IBookService {
	private static int size = 20;
	@Autowired
	private BookRepository bookRepository;
	@Autowired
	private PdfRepository pdfRepository;
	@Autowired
	private AudioRepository audioRepository;
	@Autowired
	private OnlineRepository onlineRepository;
	@Autowired
	private Converter bookConverter;

	// Get read format of book
	public List<String> getReadFromat(int bookId) {
		List<String> readFormat = new ArrayList<String>();
		List<PdfEntity> pdfs = pdfRepository.findByBookId(bookId);
		if (pdfs.size() > 0) {
			readFormat.add("Đọc PDF");
		}
		List<OnlineEntity> onlines = onlineRepository.findByBookId(bookId);

		if (onlines.size() > 0) {
			readFormat.add("Đọc online");
		}
		List<AudioEntity> audios = audioRepository.findByBookId(bookId);
		if (audios.size() > 0) {
			readFormat.add("Nghe audio");
		}
		return readFormat;

	}

	// Paging book entity
	@Override
	public Pageable getBookEntitesByPage(int page) {
		Pageable pageable = PageRequest.of(page, size);
		return pageable;
	}

	@Override
	// Get books + paging
	public Page<BookDTO> getBooksByPage(int page) {
		Page<BookEntity> entities = bookRepository.findAll(getBookEntitesByPage(page));

		Page<BookDTO> books = entities.map(entity -> {
			BookDTO dto = bookConverter.toDTO(entity);

			// set read format
			dto.setReadFormat(getReadFromat(entity.getId()));
			return dto;
		});

		return books;

	}

	// Get authors of book by book's id
	@Override
	public List<String> getAuthorsOfBook(int id) {
		return bookRepository.getAuthorsOfBook(id);

	}

	// Get categories of book by book's id
	@Override
	public List<String> getCategoriesOfBook(int id) {
		return bookRepository.getCategoriesOfBook(id);
	}

	@Override
	public void setBookCategoriesAndAuthors(List<BookDTO> books) {
		for (BookDTO book : books) {
			List<String> bookAuthors = getAuthorsOfBook(book.getId());
			List<String> bookCategories = getCategoriesOfBook(book.getId());

			book.setCategories(bookCategories);
			book.setAuthors(bookAuthors);

		}
	}

	@Override
	public Page<BookDTO> search(String bookName, String authorName, int page) {
		
		Pageable pageable = PageRequest.of(page, size);

		Page<BookEntity> entities = bookRepository.search(bookName, authorName, pageable);

		Page<BookDTO> books = entities.map(entity -> {
			BookDTO dto = bookConverter.toDTO(entity);

			// set read format
			dto.setReadFormat(getReadFromat(entity.getId()));
			return dto;
		});

		return books;

	}

}
