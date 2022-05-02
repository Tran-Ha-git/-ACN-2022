package com.web.dacn.dto;

import java.sql.Date;
import java.util.List;
import java.util.Set;

import com.web.dacn.entity.AuthorEntity;
import com.web.dacn.entity.quote.Quote;
import com.web.dacn.entity.user.Role;
import com.web.dacn.entity.user.User;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookDTO {
	private int id;
	private String name;
	private String thumbnail;
	
	private Date modTime;
	private List<String> readFormat;
	private String status;
	private List<String> categories;
	private List<String> authors;
	

	public BookDTO(int id, String name, String thumbnail, Date mod_time) {
		super();
		this.id = id;
		this.name = name;
		this.thumbnail = thumbnail;
		this.modTime = mod_time;

	}

}
