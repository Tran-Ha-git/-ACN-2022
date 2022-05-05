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
	private Integer view;
	private String slug;
	private String metaTitle;
	private String metaDescription;
	private Integer status;
    private Set<BookDTO> books;
}
