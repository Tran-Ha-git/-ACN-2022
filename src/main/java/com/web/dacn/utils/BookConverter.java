package com.web.dacn.utils;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.web.dacn.dto.BookDto;
import com.web.dacn.entity.Author;
import com.web.dacn.entity.Book;

@Service
public class BookConverter {
	public Book convertToEntity(BookDto bookDto) {
		Book entity = new Book();
		entity.setId(bookDto.getId());
		entity.setName(bookDto.getName());
		entity.setThumbnail(bookDto.getThumbnail());
		entity.setDescription(bookDto.getDescription());
		entity.setSlug(bookDto.getSlug());
		entity.setMeta_description(bookDto.getMetaDescription());
		entity.setMeta_title(bookDto.getMetaTitle());
		entity.setId(bookDto.getId());
		entity.setId(bookDto.getId());
		return entity;
	}
	
	public BookDto convertToDto(Book book) {
		BookDto dto = new BookDto();
		dto.setId(book.getId());
		dto.setName(book.getName());
		dto.setThumbnail(book.getThumbnail());
		dto.setDescription(book.getDescription());
		dto.setSlug(book.getSlug());
		dto.setMetaDescription(book.getMeta_description());
		dto.setMetaTitle(book.getMeta_title());
		dto.setId(book.getId());
		dto.setId(book.getId());
		
		return dto;
	}
}
