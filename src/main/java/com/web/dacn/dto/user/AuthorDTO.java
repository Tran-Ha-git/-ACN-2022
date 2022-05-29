package com.web.dacn.dto.user;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.validation.constraints.NotBlank;

import com.web.dacn.dto.book.BookDTO;
import com.web.dacn.dto.quote.QuoteDto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuthorDTO implements Serializable{
	private Long id;
	@NotBlank(message = "Slug must be not blank")
	private String slug;
	
	@NotBlank(message = "Fullname must be not blank")
	private String fullname;
	
	private Date birthday;
	private String address;	
	private String phone;
	
	@NotBlank(message = "Description must be not blank")
	private String description;
	
	private String metaTitle;
	private String metaDescription;

	private Integer status;
	private Date modTime;
	private UserDto user;
	private List<BookDTO> books = new ArrayList<>();	
	private List<QuoteDto> quotes = new ArrayList<>();
}
