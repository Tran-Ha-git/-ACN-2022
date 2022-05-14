package com.web.dacn.controller.admin;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.sun.xml.messaging.saaj.packaging.mime.internet.ParseException;
import com.web.dacn.dto.book.BookDTO;
import com.web.dacn.entity.book.Audio;
import com.web.dacn.entity.book.Book;
import com.web.dacn.service.admin.IAudioService;
import com.web.dacn.service.admin.impl.BookService;
import com.web.dacn.utils.UploadFile;

@Controller
@RequestMapping("/admin")
public class EditBookController {
	@Autowired

	private BookService bookService;

	@Autowired
	private IAudioService audioService;
	
//	@RequestMapping(value = "/editBook/{id}")
//	public String edit(@PathVariable("id") long id, @RequestParam(name = "name") String name,
//			@RequestParam(name = "description") String description, @RequestParam(name = "thumbnail") String thumbnail,
//			@RequestParam(name = "status") String status, @RequestParam(name = "price") String price,
//			@RequestParam(name = "slug") String slug, @RequestParam(name = "meta_Title") String meta_title,
//			@RequestParam(name = "meta_Description") String meta_description,
//
//			ModelMap model, HttpServletRequest request, HttpSession session) throws ParseException {
//
//		String vips = request.getParameter("vip");
//		boolean vip = false;
//		if (vips != null) {
//			vip = true;
//		}
//
//		// boolean vipB= Boolean.parseBoolean(vip);
//		double priceB = Double.parseDouble(price);
//		Date date = new Date(24, 4, 2022);
//		int sta = bookService.convertStatus(status);
//
//		// sd @manyToMany
//		Book book = new Book();
//		book.setName(name);
//		book.setThumbnail(thumbnail);
//		book.setDescription(description);
//		book.setMetaTitle(meta_title);
//		book.setMetaDescription(meta_description);
//		book.setMod_time(date);
//		book.setPrice(priceB);
//		book.setSlug(slug);
//		book.setVip(vip);
//		book.setStatus(sta);
//
//		book.setMod_usre_id(4L);
//
//		Author author = new Author();
//		// author.setFullname(fullname);
//		BookCategory bookcategory = new BookCategory();
//		// bookcategory.setName(nameCategory);
//		bookService.updateBook(id, book);
//
//		return "redirect:/admin/books";
//
//	}

//hiển thị list online
	@RequestMapping(value = "/editBook/listChapter")
	public String getChapters(ModelMap model, HttpSession session, HttpServletRequest request) throws ParseException {

		String id = request.getParameter("id");
		long bId = Long.parseLong(id);

		BookDTO bookDto = bookService.findById(bId);
		model.addAttribute("book", bookDto);
		session.setAttribute("id", id);
		session.setAttribute("book", bookDto);

		return "adminListChapterOnline";
	}
    


}
