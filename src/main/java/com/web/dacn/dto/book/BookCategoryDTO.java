package com.web.dacn.dto.book;

import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookCategoryDTO {
	private Long id;
	@NotEmpty
	@NotNull
	private String name;
	private BookCategoryDTO parentBookCategory;
	private Integer view;
	@NotEmpty
	@NotNull
	private String slug;
	private String metaTitle;
	private String metaDescription;
	@Min(value = 0)
	@Max(value=1)
	@NotNull
	private Integer status;
    private List<BookDTO> books = new ArrayList<>();
}
