package com.web.dacn.controller;

import java.net.http.HttpRequest;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.web.dacn.dto.BookDTo;
import com.web.dacn.dto.BooksDTO;
import com.web.dacn.repository.BookRepository;
import com.web.dacn.service.BookService;

@Controller
@RequestMapping("/admin")
public class BookManagementController {

	@Autowired

	private BookService bookService;
	@Autowired
	private BookRepository bookRepo;

	private BookDTo bookDto;

	@RequestMapping("/books")
	public String getBooks(Model model, HttpSession session, HttpServletRequest request) {
		List<BooksDTO> books = bookService.getBooks();
		String action = request.getParameter("action");

		for (BooksDTO book : books) {
			List<String> bookAuthors = bookService.getAuthorsOfBook(book.getId());
			List<String> bookCategories = bookService.getCategoriesOfBook(book.getId());

			// set categories and authors for book
			book.setCategories(bookCategories);
			book.setAuthors(bookAuthors);

		}
//		if(action!=null) {
//			if(action.equals("DeleteAll")) {
//				String[] paramId = request.getParameterValues("delete");
//				if (paramId != null)
//					for (String param : paramId) {
//                       
//					
//						int cId = Integer.parseInt(param);
//						bookService.deleteByIdPdf(cId);
//						bookService.deleteByIdAudio(cId);
//						bookService.deleteByIdBookAuthor(cId);
//						bookService.deleteByIdBookBookCategory(cId);
//						bookService.deleteBook(cId);
//					}
//			}
//		}

		model.addAttribute("books", books);
		session.setAttribute("books", books);

		return "adminListBook";
	}

//	@RequestMapping("/search")
//	public String search(@RequestParam(name = "bookName") String bookName) {
//		List<BookDTo> books = (List<BookDTo>) session.getAttribute("books");
//
//		return "adminListBook";
//	}
//delete book =id
	@RequestMapping(value = "/DeleteBook")
	public String delete(@RequestParam(name = "id") String id, ModelMap model, HttpSession session) {
		int cId = Integer.parseInt(id);
		bookService.deleteByIdPdf(cId);
		bookService.deleteByIdAudio(cId);
		bookService.deleteByIdOnline(cId);
		bookService.deleteByIdBookAuthor(cId);
		
		bookService.deleteByIdBookBookCategory(cId);
		bookService.deleteBook(cId);
		List<BooksDTO> list = bookService.getBooks();
		for (BooksDTO book : list) {
			List<String> bookAuthors = bookService.getAuthorsOfBook(book.getId());
			List<String> bookCategories = bookService.getCategoriesOfBook(book.getId());

			// set categories and authors for book
			book.setCategories(bookCategories);
			book.setAuthors(bookAuthors);

		}

		session.setAttribute("books", list);
		model.addAttribute("books", list);
		return "adminListBook";

	}
	// delete check checkbox

	@RequestMapping(value = "/DeleteAll")
	public String deleteAll(ModelMap model, HttpSession session, HttpServletRequest request) {
		String[] paramId = request.getParameterValues("delete");
		if (paramId != null)
			for (String param : paramId) {

				int cId = Integer.parseInt(param);
				bookService.deleteByIdPdf(cId);
				bookService.deleteByIdAudio(cId);
				bookService.deleteByIdBookAuthor(cId);
				bookService.deleteByIdBookBookCategory(cId);
				bookService.deleteBook(cId);
			}
		return "redirect:/admin/books";

	}

	@RequestMapping(value = "/books/addbook")
	public String Add(ModelMap model, HttpSession session, HttpServletRequest request) {

		return "adminNewBook";

	}

	@RequestMapping(value = "/edit")
	public String Edit(ModelMap model, HttpSession session, HttpServletRequest request) {
		String id = request.getParameter("id");
		int bId = Integer.parseInt(id);

		bookDto = bookService.findById(bId);
		bookDto.setAuthor(bookService.getAuthorsOfBook(bId));
		bookDto.setCategory(bookService.getCategoriesOfBook(bId));

		bookDto.setPdf(bookService.getPdfOfBook(bId));
		model.addAttribute("book", bookDto);

		return "editForm";

	}

}
