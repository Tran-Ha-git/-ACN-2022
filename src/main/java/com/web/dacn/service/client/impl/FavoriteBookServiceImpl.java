package com.web.dacn.service.client.impl;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.web.dacn.dto.book.BookCategoryDTO;
import com.web.dacn.dto.book.BookDTO;
import com.web.dacn.dto.user.AuthorDTO;
import com.web.dacn.entity.book.Book;
import com.web.dacn.entity.user.User;
import com.web.dacn.repository.BookRepository;
import com.web.dacn.repository.FavoriteBookRepository;
import com.web.dacn.service.client.FavoriteBookService;

@Service
public class FavoriteBookServiceImpl implements FavoriteBookService {
	@Autowired
	private HttpSession session;

	@Autowired
	private FavoriteBookRepository favoriteBookRepository;
	
	@Autowired
	private BookRepository bookRepository;

	@Override
	public Page<BookDTO> getLoveBook(String search, int page) {
		User loginedUser = (User) session.getAttribute("user");
		
		Pageable pageable = PageRequest.of(page, 4, Sort.by("id").descending());// page number , 4 items
		
		if(loginedUser == null) return null;

		 Page<Book> books= bookRepository.findByFavoriteBooks_UserIdAndNameContainingIgnoreCaseAndStatus(loginedUser.getId(), search, 1, pageable);
		 
		 List<BookDTO> bookDtos= books.getContent().stream().map(entity ->{
			 BookDTO dto = new BookDTO();
			 dto.setId(entity.getId());
			 dto.setThumbnail(entity.getThumbnail());
			 dto.setName(entity.getName());
			 dto.setSlug(entity.getSlug());
			 
			 entity.getAuthors().forEach(author->{
				 AuthorDTO authorDTO = new AuthorDTO();
				 authorDTO.setId(author.getId());
				 authorDTO.setSlug(author.getSlug());
				 authorDTO.setFullname(author.getFullname());
				 dto.getAuthors().add(authorDTO);
			 });
			 
			 entity.getCategories().forEach(category->{
				 BookCategoryDTO categoryDTO = new BookCategoryDTO();
				 categoryDTO.setId(category.getId());
				 categoryDTO.setSlug(category.getSlug());
				 categoryDTO.setName(category.getName());
				 dto.getCategories().add(categoryDTO);
			 });
			 return dto;
		 }).collect(Collectors.toList());
		 
		 Page<BookDTO> results = new PageImpl<>(bookDtos, pageable, books.getTotalElements());;
		 
		return results;
	}

}
