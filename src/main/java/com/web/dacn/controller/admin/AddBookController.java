package com.web.dacn.controller.admin;

import java.io.IOException;
import java.sql.Date;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.sun.xml.messaging.saaj.packaging.mime.internet.ParseException;
import com.web.dacn.entity.book.Book;
import com.web.dacn.entity.user.User;
import com.web.dacn.repository.UserRepository;
import com.web.dacn.service.admin.impl.BookService;
import com.web.dacn.utils.UploadFile;

@Controller
@RequestMapping("/admin")
public class AddBookController {
	@Autowired
	private BookService bookService;
	@Autowired
	private UploadFile uploadFile;
	@Autowired
	private UserRepository userRepository;

	@RequestMapping(value = "/books/add")

	public String add(@ModelAttribute Book book, ModelMap model, HttpServletRequest request,
			@RequestParam("fileUpload") MultipartFile file) throws ParseException, IOException {
		String vip = request.getParameter("vip");

		if (vip != null) {
			book.setVip(true);
		}
//     String authorName = request.getParameter("fullname");
//     String categoryName = request.getParameter("nameCategory");

		String serverImageFilePath = uploadFile.upload(file, request,"/assets/images/");

		if (serverImageFilePath == null) {
			model.addAttribute("message", "Your image has not been upload yet");

			return "message";

		}

		long millis = System.currentTimeMillis();
		Date date = new Date(millis);

		// sd @manyToMany
		User user = userRepository.findUserById(1L);
		;

		book.setMod_time(date);
		book.setUser(user);
		book.setThumbnail(serverImageFilePath);

		bookService.addBook(book);

		return "redirect:/admin/books";

	}
}
