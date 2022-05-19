package com.web.dacn.utils;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.stereotype.Component;

import com.web.dacn.dto.book.AudioDTO;
import com.web.dacn.dto.book.BookCategoryDTO;
import com.web.dacn.dto.book.BookDTO;
import com.web.dacn.dto.book.OnlineDTO;
import com.web.dacn.dto.book.PdfDTO;
import com.web.dacn.dto.user.AuthorDTO;
import com.web.dacn.entity.book.Audio;
import com.web.dacn.entity.book.Book;
import com.web.dacn.entity.book.BookCategory;
import com.web.dacn.entity.book.Online;
import com.web.dacn.entity.book.Pdf;
import com.web.dacn.entity.user.Author;

@Component
public class Converter {
	public AuthorDTO toDTO(Author entity) {
		AuthorDTO author = new AuthorDTO();
		author.setFullname(entity.getFullname());
		author.setId(entity.getId());
		return author;

	}

	public OnlineDTO toDTO(Online entity) {
		OnlineDTO online = new OnlineDTO();
		online.setName(entity.getName());
		online.setPriority(entity.getPriority());
		online.setId(entity.getId());
		return online;

	}

	public AudioDTO toDTO(Audio entity) {
	
		AudioDTO audio = new AudioDTO();
		audio.setName(entity.getName());
		audio.setPriority(entity.getPriority());
		audio.setUrl(entity.getUrl());		
		audio.setId(entity.getId());
	
		return audio;

	}

	public BookCategoryDTO toDTO(BookCategory entity) {
		BookCategoryDTO category = new BookCategoryDTO();
		category.setId(entity.getId());
		category.setName(entity.getName());

		return category;
	}

	public PdfDTO toDTO(Pdf entity) {
		PdfDTO pdf = new PdfDTO();
		pdf.setId(entity.getId());
		pdf.setUrl(entity.getUrl());

		return pdf;
	}

	public BookDTO toDTO(Book entity) {

		BookDTO book = new BookDTO();
		
		
		
		book.setId(entity.getId());
		book.setName(entity.getName());
		book.setStatus(entity.getStatus());
		book.setThumbnail(entity.getThumbnail());
		book.setMod_time(entity.getMod_time());
		book.setSlug(entity.getSlug());
		book.setMetaDescription(entity.getMetaDescription());
		book.setDescription(entity.getDescription());
		book.setMetaTitle(entity.getMetaTitle());
		book.setVip(entity.getVip());
		book.setPrice(entity.getPrice());

		List<BookCategoryDTO> categories = new ArrayList<BookCategoryDTO>();
		for (BookCategory category : entity.getCategories()) {
			BookCategoryDTO dto = toDTO(category);
			categories.add(dto);

		}

		List<AuthorDTO> authors = new ArrayList<AuthorDTO>();
		for (Author author : entity.getAuthors()) {
			AuthorDTO dto = toDTO(author);
			authors.add(dto);

		}
		List<PdfDTO> pdfs = new ArrayList<PdfDTO>();
		for (Pdf pdf : entity.getPdfs()) {
			PdfDTO dto = toDTO(pdf);
			pdfs.add(dto);
		}
		List<OnlineDTO> onlines = new ArrayList<OnlineDTO>();
		for (Online online : entity.getOnlines()) {
			OnlineDTO dto = toDTO(online);
			onlines.add(dto);

		}
		List<AudioDTO> audios = new ArrayList<AudioDTO>();
		for (Audio audio : entity.getAudios()) {
			AudioDTO dto = toDTO(audio);
			audios.add(dto);

		}
	
		book.setCategories(categories);
		book.setAuthors(authors);
		book.setAudios(audios);
		book.setOnlines(onlines);
		book.setPdfs(pdfs);
		

		return book;
	}
}
