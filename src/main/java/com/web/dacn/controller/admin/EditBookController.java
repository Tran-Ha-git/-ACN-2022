package com.web.dacn.controller.admin;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.sun.xml.messaging.saaj.packaging.mime.internet.ParseException;
import com.web.dacn.dto.book.BookDTO;
import com.web.dacn.entity.book.Book;
import com.web.dacn.entity.book.BookCategory;
import com.web.dacn.entity.user.Author;
import com.web.dacn.entity.user.User;
import com.web.dacn.repository.UserRepository;
import com.web.dacn.service.admin.IAudioService;
import com.web.dacn.service.admin.impl.BookService;
import com.web.dacn.service.auth.UserService;
import com.web.dacn.utils.UploadFile;

@Controller
@RequestMapping("/admin")
public class EditBookController {
	@Autowired

	private BookService bookService;
	@Autowired
	private UploadFile uploadFile;
	@Autowired
	private UserService userService;

	@RequestMapping(value = "/editBook/{id}")
	public String edit(@PathVariable("id") long id, @ModelAttribute Book book,
			@RequestParam("fileUpload") MultipartFile file, ModelMap model, HttpServletRequest request,
			HttpSession session) throws ParseException, IOException {
		String authorid = request.getParameter("authorid");
		String authorName = request.getParameter("fullname");
		long authorId = 0L;
		if (authorid != null) {
			authorId = Long.parseLong(authorid);
		}

		String categoryid = request.getParameter("categoryid");
		String categoryName = request.getParameter("categoryName");
		long categoryId = 0L;
		if (categoryid != null) {
			categoryId = Long.parseLong(categoryid);
		}

		Book newBook = bookService.getBookById(id);

		String vip = request.getParameter("vip");
		if (vip != null) {
			newBook.setVip(true);
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
		newBook.setName(book.getName());
		newBook.setDescription(book.getDescription());
		newBook.setStatus(book.getStatus());
		newBook.setPrice(book.getPrice());
		newBook.setSlug(book.getSlug());
		newBook.setMetaDescription(book.getMetaDescription());
		newBook.setMetaTitle(book.getMetaTitle());
		newBook.setModTime(date);
		newBook.setUser(user);

		if (!file.isEmpty()) {
			newBook.setThumbnail(serverImageFilePath);
		}
		// Author
		if (authorName != null) {
			Author author = bookService.findAuthorByFullName(authorName);
			Author authorBeforeChange = bookService.findAuthorById(authorId);
			newBook.getAuthors().remove(authorBeforeChange);
			if (author != null) {
				newBook.getAuthors().add(author);
			}

			else {
				Author newAuthor = new Author();

				newAuthor.setFullname(authorName);
				newAuthor.setDescription("default");
				newAuthor.setSlug(authorName.trim().replaceAll(" ", "-"));
				newAuthor.setModTime(date);
				newAuthor.setUser(user);

				bookService.saveAuthor(newAuthor);
				newBook.getAuthors().add(newAuthor);
			}
		}

		if (categoryName != null) {

			List<BookCategory> categories = bookService.findCategoryByName(categoryName);
			BookCategory categoryBeforeChange = bookService.findCategoryById(categoryId);
			newBook.getCategories().remove(categoryBeforeChange);

			if (categories.size() == 0) {
				BookCategory newCategory = new BookCategory();
				newCategory.setName(categoryName);
				newCategory.setSlug(categoryName.trim().replaceAll(" ", "-"));

				bookService.saveCategory(newCategory);
				newBook.getCategories().add(newCategory);

			} else {

				BookCategory category = categories.get(0);
				newBook.getCategories().add(category);
			}

		}

		bookService.saveBook(newBook);
		}
	
		return "redirect:/admin/books";

	}

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
