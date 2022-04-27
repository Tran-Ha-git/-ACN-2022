package com.web.dacn.controller;

import java.sql.Date;
import java.text.ParseException;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.web.dacn.entity.BookEntity;
import com.web.dacn.repository.BookRepository;
import com.web.dacn.service.BookService;

@Controller
@RequestMapping("/admin")

public class EditBookController {
	@Autowired

	private BookService bookService;

	@Autowired
	private BookRepository bookRepository;

	@RequestMapping(value = "/editBook")
	public String edit(  ) {
//		String id = request.getParameter("id");
//		int bId = Integer.parseInt(id);
//		String vips = request.getParameter("vip");
//		boolean vip = false;
//		if (vips != null) {
//			vip = true;
//		}
//		float priceB = Float.parseFloat(price);

		// int sta = bookService.convertStatus(status);
//		bookRepository.updateTitle(bId, name, priceB, vip, description, slug, meta_title, meta_description, sta);
//		bookRepository.update(bId);

			
		return "editForm";

	}
}
