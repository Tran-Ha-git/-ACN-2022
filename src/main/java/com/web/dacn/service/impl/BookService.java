package com.web.dacn.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.web.dacn.dto.BookDto;
import com.web.dacn.entity.Author;
import com.web.dacn.entity.Book;
import com.web.dacn.entity.Bookcategory;
import com.web.dacn.repository.BookRepository;
import com.web.dacn.service.IBookService;
import com.web.dacn.utils.BookConverter;

@Service
public class BookService implements IBookService{
	@Autowired
	private BookRepository bookRepository;
	
	@Autowired
	private BookConverter bookConverter;

	@Override
	public BookDto getBookById(Long id) {
		Book book = bookRepository.findById(id).orElse(null);
		
		if(book!=null) {
			BookDto dto = bookConverter.convertToDto(book);
			dto.setCategories(getCategoryNames(book));
			dto.setListCategory(String.join(", ", dto.getCategories()));
			
			dto.setAuthors(getAuthorNames(book));
			dto.setListAuthor(String.join(", ", dto.getAuthors()));
			return dto;
		}
		return null;
	}

	@Override
	public BookDto getBookBySlug(String slug) {
		Book book = bookRepository.findBySlug(slug);
		if(book!=null) {
			return bookConverter.convertToDto(book);
		}
		return null;
	}

	@Override
	public List<BookDto> getAll(Integer pageNo, Integer pageSize, String sortBy) {
		List<BookDto> results = new ArrayList<BookDto>();
		Pageable paging = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));
		
		Page<Book> books = bookRepository.findAll(paging);
		
//		if(books!=null&& books.size()>0) {
//			for (Book book : books) {
//				BookDto dto = bookConverter.convertToDto(book);
//				results.add(dto);
//			}
//		}
		if(books.hasContent()) {
			
			for (Book book : books) {
				BookDto dto = bookConverter.convertToDto(book);
				dto.setCategories(getCategoryNames(book));
				dto.setListCategory(String.join(",", dto.getCategories()));
				results.add(dto);
			}
		}
		return results;
	}
	
	@Override
	public List<BookDto> getAllBookAudio(Integer pageNo, Integer pageSize, String sortBy) {
		List<BookDto> results = new ArrayList<BookDto>();
		Pageable paging = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));
		
		Page<Book> books = bookRepository.findDistinctByAudiosNotNull(paging);
		
		if(books.hasContent()) {
			
			for (Book book : books) {
				BookDto dto = bookConverter.convertToDto(book);
				dto.setCategories(getCategoryNames(book));
				dto.setListCategory(String.join(",", dto.getCategories()));
				results.add(dto);
			}
		}
		return results;
	}
	
	private List<String> getAuthorNames(Book book ){
		List<String> authornames = new ArrayList<>();
		for (Author author : book.getAuthors()) {
			authornames.add(author.getFullname());
		}
		return authornames;
	}
	
	private List<String> getCategoryNames(Book book ){
		List<String> categoryNames = new ArrayList<>();
		for (Bookcategory author : book.getCategories()) {
			categoryNames.add(author.getName());
		}
		return categoryNames;
	}
}
