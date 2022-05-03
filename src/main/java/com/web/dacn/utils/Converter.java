package com.web.dacn.utils;

import java.util.HashSet;
import java.util.Set;

import org.springframework.stereotype.Component;

import com.web.dacn.dto.book.BookCategoryDTO;
import com.web.dacn.dto.book.BookDTO;
import com.web.dacn.dto.user.AuthorDTO;
import com.web.dacn.entity.book.Book;
import com.web.dacn.entity.book.BookCategory;
import com.web.dacn.entity.user.Author;

@Component
public class Converter {
	public AuthorDTO toDTO(Author entity) {
		AuthorDTO author = new AuthorDTO();
		author.setFullname(entity.getFullname());
		author.setId(entity.getId());
		return author;

	}

	public BookCategoryDTO toDTO(BookCategory entity) {
		BookCategoryDTO category = new BookCategoryDTO();
		category.setId(entity.getId());
		category.setName(entity.getName());
 
		
		return category;
	}

	public BookDTO toDTO(Book entity) {
	
		
		BookDTO book = new BookDTO(entity.getId(), entity.getName(),entity.getStatus(), entity.getThumbnail(), entity.getModTime());
		Set<BookCategoryDTO> categories= new HashSet<BookCategoryDTO>();
        for(BookCategory category: entity.getCategories()) {
        	BookCategoryDTO dto = toDTO(category);
        	categories.add(dto);    	
        	
        }
		
        
        Set<AuthorDTO> authors= new HashSet<AuthorDTO>();
        for(Author author: entity.getAuthors()) {
        	AuthorDTO dto = toDTO(author);
        	authors.add(dto);    	
        	
        }
		 book.setCategories(categories);
		 book.setAuthors(authors);
		
		// Convert status form int to String to display in UI
		switch (entity.getStatus()) {
		case 0:
			book.setStatusConvert("Đã xóa");
			break;
		case 1:
			book.setStatusConvert("Hoàn thành");
			break;
		case 2:
			book.setStatusConvert("Đang update");
			break;
		default:
			book.setStatusConvert("");
		}

		return book;
	}
}
