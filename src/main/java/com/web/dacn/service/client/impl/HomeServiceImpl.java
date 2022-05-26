package com.web.dacn.service.client.impl;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.domain.Sort.Order;
import org.springframework.stereotype.Service;

import com.web.dacn.dto.book.BookCategoryDTO;
import com.web.dacn.dto.book.BookDTO;
import com.web.dacn.dto.quote.QuoteDto;
import com.web.dacn.dto.user.AuthorDTO;
import com.web.dacn.dto.user.UserDto;
import com.web.dacn.entity.book.Book;
import com.web.dacn.entity.book.BookCategory;
import com.web.dacn.entity.quote.Quote;
import com.web.dacn.entity.user.User;
import com.web.dacn.repository.BookRepository;
import com.web.dacn.repository.QuoteRepository;
import com.web.dacn.service.client.HomeService;

@Service
public class HomeServiceImpl implements HomeService{
	
	@Autowired
	private BookRepository bookRepository;
	
	@Autowired
	private QuoteRepository quoteRepository;

	@Override
	public List<BookDTO> getNewBooks() {
		Page<Book> booksPage = bookRepository.findBookContainingSearchOrderBySort("", PageRequest.of(0, 16, Sort.by("mod_time").descending()));
		return convertBookEntityToBookDTO(booksPage).getContent();
	}

	@Override
	public List<BookDTO> getMangaBookByTopView() {
		return bookRepository.findBookByCategoryIdAndContainingSearchOrderBySort("", PageRequest.of(0, 16, Sort.by("view").descending()), 1L).stream().map(book -> {
			BookDTO dto = new BookDTO();
			BeanUtils.copyProperties(book, dto);
			book.getAuthors().stream().forEach(author -> {
				AuthorDTO authorDTO = new AuthorDTO();
				BeanUtils.copyProperties(author, authorDTO);
				dto.getAuthors().add(authorDTO);
			});
			book.getCategories().stream().forEach(category -> {
				BookCategoryDTO bookCategoryDTO = new BookCategoryDTO();
				BeanUtils.copyProperties(category, bookCategoryDTO);
				dto.getCategories().add(bookCategoryDTO);
			});

			return dto;
		}).collect(Collectors.toList());
	}

	@Override
	public List<BookDTO> getAudioBookByTopView() {
		return bookRepository.findAudioBookContainingSearchOrderBySort("", PageRequest.of(0, 10, Sort.by("view").descending())).stream().map(book -> {
			BookDTO dto = new BookDTO();
			BeanUtils.copyProperties(book, dto);
			book.getAuthors().stream().forEach(author -> {
				AuthorDTO authorDTO = new AuthorDTO();
				BeanUtils.copyProperties(author, authorDTO);
				dto.getAuthors().add(authorDTO);
			});
			book.getCategories().stream().forEach(category -> {
				BookCategoryDTO bookCategoryDTO = new BookCategoryDTO();
				BeanUtils.copyProperties(category, bookCategoryDTO);
				dto.getCategories().add(bookCategoryDTO);
			});

			return dto;
		}).collect(Collectors.toList());
	}

	@Override
	public List<BookDTO> getEbookBookByTopView() {
		return bookRepository.findPdfBookContainingSearchOrderBySort("", PageRequest.of(0, 10, Sort.by("view").descending())).stream().map(book -> {
			BookDTO dto = new BookDTO();
			BeanUtils.copyProperties(book, dto);
			book.getAuthors().stream().forEach(author -> {
				AuthorDTO authorDTO = new AuthorDTO();
				BeanUtils.copyProperties(author, authorDTO);
				dto.getAuthors().add(authorDTO);
			});
			book.getCategories().stream().forEach(category -> {
				BookCategoryDTO bookCategoryDTO = new BookCategoryDTO();
				BeanUtils.copyProperties(category, bookCategoryDTO);
				dto.getCategories().add(bookCategoryDTO);
			});

			return dto;
		}).collect(Collectors.toList());
	}

	@Override
	public List<QuoteDto> getQuoteByTopView() {
		Page<Quote> quotesPage = quoteRepository.findAll(PageRequest.of(0, 10, Sort.by("view").descending()));		
		return convertQuoteEntityToQuoteDTO(quotesPage).getContent();
	}
	
	private Page<BookDTO> convertBookEntityToBookDTO(Page<Book> page) {
		return page.map(new Function<Book, BookDTO>() {
		    @Override
		    public BookDTO apply(Book entity) {
		    	BookDTO bookDTO = new BookDTO();
		    	BeanUtils.copyProperties(entity, bookDTO);
		    	
		    	UserDto userDTO =  new UserDto();
		    	User user = entity.getUser();
		    	if(user != null) {
			    	BeanUtils.copyProperties(user, userDTO);
			    	bookDTO.setUser(userDTO);		    		
		    	}
		    	
		    	List<BookCategory> listBookCategories = entity.getCategories();
		    	List<BookCategoryDTO> listBookCategoryDTOs = listBookCategories.stream().map(e -> {
		    		BookCategoryDTO bookCategoryDTO = new BookCategoryDTO();
		    		BeanUtils.copyProperties(e, bookCategoryDTO);
		    		return bookCategoryDTO;
		    	}).collect(Collectors.toList());
		    	bookDTO.setCategories(listBookCategoryDTOs);
		    	
		    	return bookDTO;
		    }
		});	
	}

	private Page<QuoteDto> convertQuoteEntityToQuoteDTO(Page<Quote> page) {
		return page.map(new Function<Quote, QuoteDto>() {
			@Override
			public QuoteDto apply(Quote t) {
				QuoteDto dto = new QuoteDto();
				BeanUtils.copyProperties(t, dto);
				return dto;
			}
		});
	}

	@Override
	public List<BookDTO> getSuggestBook() {
		Page<Book> booksPage = bookRepository.findAll(PageRequest.of(0, 16, Sort.by("view").descending()));
		return convertBookEntityToBookDTO(booksPage).getContent();
	}
}
	