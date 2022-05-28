package com.web.dacn.controller.admin;

import java.io.IOException;
import java.sql.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.sun.xml.messaging.saaj.packaging.mime.internet.ParseException;
import com.web.dacn.entity.book.Book;
import com.web.dacn.entity.book.BookCategory;
import com.web.dacn.entity.user.Author;
import com.web.dacn.entity.user.User;
import com.web.dacn.service.admin.impl.BookService;
import com.web.dacn.service.auth.UserService;
import com.web.dacn.utils.UploadFile;

@Controller
@RequestMapping("/admin")
public class AddBookController {
	@Autowired
	private BookService bookService;
	@Autowired
	private UploadFile uploadFile;
	@Autowired
	private UserService userService;

	@RequestMapping(value = "/books/add")

	public String add(@ModelAttribute Book book, ModelMap model, HttpServletRequest request,
			@RequestParam("fileUpload") MultipartFile file, HttpSession session) throws ParseException, IOException {
		String authorName = request.getParameter("fullname");
		String categoryName = request.getParameter("categoryName");
		String vip = request.getParameter("vip");

		if (vip != null) {
			book.setVip(true);
		}

		User user = (User) session.getAttribute("user");
		if (user != null) {
			book.setUser(user);

			long millis = System.currentTimeMillis();
			Date date = new Date(millis);
			String serverImageFilePath = uploadFile.upload(file, request, "/assets/images/");

			if (serverImageFilePath == null) {
				model.addAttribute("message", "Your image has not been upload yet");

				return "message";

			}
			book.setModTime(date);
			book.setUser(user);
			book.setThumbnail(serverImageFilePath);

			Author author = bookService.findAuthorByFullName(authorName);
			if (author != null) {
				book.getAuthors().add(author);
			} else {
				Author newAuthor = new Author();

				newAuthor.setFullname(authorName);
				newAuthor.setDescription("default");
				newAuthor.setSlug(authorName.trim().replaceAll(" ", "-"));
				newAuthor.setModTime(date);
				newAuthor.setUser(user);

				bookService.saveAuthor(newAuthor);
				book.getAuthors().add(newAuthor);

			}

			List<BookCategory> categories = bookService.findCategoryByName(categoryName);

			if (categories.size() > 0) {
				BookCategory category = categories.get(0);
				// category.getBooks().add(book);
				book.getCategories().add(category);

			} else {
				BookCategory newCategory = new BookCategory();

				newCategory.setName(categoryName);
				newCategory.setSlug(categoryName.trim().replaceAll(" ", "-"));

				bookService.saveCategory(newCategory);
				// newCategory.getBooks().add(book);
				book.getCategories().add(newCategory);

			}

			bookService.saveBook(book);
		}
		return "redirect:/admin/books";

	}

}
