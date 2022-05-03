package com.web.dacn.dto.book;

import java.sql.Date;

import java.util.Set;

import com.web.dacn.dto.user.AuthorDTO;
import com.web.dacn.dto.user.UserDto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookDTO {
	private Long id;
	private String name;
	private String thumbnail;
	private int view;
	private double price;
	private boolean vip;
	private String description;
	private String slug;
	private String metaTitle;
	private String metaDescription;
	private int status;
	private Date modTime;
	private UserDto user;

	private Set<AuthorDTO> authors;
	private Set<BookCategoryDTO> categories;
	private Set<AudioDTO> audios;
	private Set<OnlineDTO> onlines;
	private Set<PdfDTO> pdfs;

}
