package com.web.dacn.dto;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.web.dacn.entity.book.Audio;
import com.web.dacn.entity.book.Book;
import com.web.dacn.entity.book.Online;
import com.web.dacn.entity.book.Pdf;
import com.web.dacn.entity.user.User;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookMarkDTO {
	private Long id;
	private Book book;
	private User user;
	private Audio audio;
	private Online online;
	private Pdf pdf;
}
