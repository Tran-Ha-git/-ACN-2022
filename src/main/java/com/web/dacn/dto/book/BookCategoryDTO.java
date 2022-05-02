package com.web.dacn.dto.book;

import java.util.Set;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookCategoryDTO {
	private Long id;
	private String name;
	private BookCategoryDTO parentBookCategory;
	private int view;
	private String slug;
	private String metaTitle;
	private String metaDescription;
	private int status;
    private Set<BookDTO> books;
}
