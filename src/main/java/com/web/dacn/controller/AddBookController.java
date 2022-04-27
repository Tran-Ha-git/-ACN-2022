package com.web.dacn.controller;

import java.sql.Date;
import java.text.ParseException;

import java.time.LocalDateTime;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.web.dacn.dto.BookDTo;
import com.web.dacn.entity.AuthorEntity;
import com.web.dacn.entity.BookCategoryEntity;
import com.web.dacn.entity.BookEntity;
import com.web.dacn.repository.AuthorRepository;
import com.web.dacn.repository.BookCategoryRepository;
import com.web.dacn.repository.BookRepository;
import com.web.dacn.service.BookService;

@Controller
@RequestMapping("/admin")
public class AddBookController {
	@Autowired

	private BookService bookService;

	@Autowired
	private BookRepository bookRepository;

	@RequestMapping(value = "/books/add")
	public String add(@RequestParam(name = "name") String name,
			@RequestParam(name = "description") String description, @RequestParam(name = "thumbnail") String thumbnail,
			@RequestParam(name = "status") String status, @RequestParam(name = "price") String price,
			@RequestParam(name = "slug") String slug, @RequestParam(name = "meta_title") String meta_title,
			@RequestParam(name = "meta_description") String meta_description,
			@RequestParam(name = "fullname") String fullname, @RequestParam(name = "nameCategory") String nameCategory,
			ModelMap model, HttpServletRequest request) throws ParseException {
		String vips = request.getParameter("vip");
		boolean vip = false;
		if (vips != null) {
			vip = true;
		}

		// boolean vipB= Boolean.parseBoolean(vip);
		float priceB = Float.parseFloat(price);
		Date date = new Date(24, 4, 2022);
		int sta = bookService.convertStatus(status);
//                   if(!bookService.checkAvailable(fullname)) {
//                	   bookService.addAuthor(name, date, "", "","", "", "", "",  1, date, 1);
//                   }
		// add bằng query tự viết
//                  bookService.addBook(name, "c://h.pgn", priceB, true, "abc", slug, meta_title, meta_description, sta, date, 1);
//                  bookService.addBookCategory(nameCategory, 1, 0, "", "", "", 1);

		// sd @manyToMany

		bookRepository
				.save(new BookEntity(name, thumbnail, priceB, vip, meta_description, slug, meta_title, meta_description,
						sta, date, 1, new AuthorEntity(fullname, date, 1), new BookCategoryEntity(nameCategory, 1)));

//                  if(!bookService.checkAvailable(nameCategory)) {
//                	  bookService.addBookCategory(nameCategory, 1, 0, "", "", "", 1);
//                  }

		return "redirect:/admin/books";

	}
}
