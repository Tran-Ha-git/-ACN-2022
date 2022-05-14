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
import com.web.dacn.entity.book.Online;
import com.web.dacn.service.admin.IOnlineService;
import com.web.dacn.service.admin.impl.BookService;

@Controller
@RequestMapping("/admin")
public class OnlineChapterController {
	@Autowired
	private BookService bookService;
	@Autowired
	private IOnlineService onlineService;
	@RequestMapping(value = "/addChapter")
	public String addChapter(@ModelAttribute Online onl, ModelMap model, HttpServletRequest request,
			HttpSession session) throws ParseException {

		String sid = (String) session.getAttribute("id");
		long id = Long.parseLong(sid);
		
		Online online = new Online();
		online.setName(onl.getName());
		online.setPriority(onl.getPriority());
		online.setContent(onl.getContent());
		
		
		Book book = bookService.getBookById(id);
		online.setBook(book);

		onlineService.save(online);
		model.addAttribute("book", book);
		return "adminListChapterOnline";

	}
	
	@RequestMapping(value = "/getEditOnlineChapter/{id}/{bookid}")
	public String getEditChapter(@PathVariable("id") long id, @PathVariable("bookid") long bookId, Model model,
			HttpSession session) {
		Online online = onlineService.findOnlineById(id);
		model.addAttribute("currentOnline", online);
		BookDTO book = bookService.findById(bookId);
		model.addAttribute("book", book);

		return "adminListChapterOnline";
	}

	@RequestMapping(value = "/editChapter/{id}")
	public String editChapterAudio(@PathVariable("id") long id,@ModelAttribute Online online,
			 ModelMap model, HttpSession session,
			HttpServletRequest request) throws ParseException, IOException {

		Online newOnline = onlineService.findOnlineById(id);

		newOnline.setName(online.getName());
		newOnline.setPriority(online.getPriority());
		newOnline.setContent(online.getContent());
		

		Book book = bookService.getBookById(newOnline.getBook().getId());
		onlineService.updateOnline(id, newOnline);
		model.addAttribute("book", book);

		return "adminListChapterOnline";

	}
	
	@RequestMapping(value = "/DeleteChapter")
	public String delete(@RequestParam(name = "id") String id,@RequestParam(name = "bookid") String bookid, ModelMap model, HttpSession session) {

		long onlineId = Long.parseLong(id);
		long bookId = Long.parseLong(bookid);
		onlineService.deleteOnline(onlineId);

		BookDTO book = bookService.findById(bookId);
		model.addAttribute("book", book);

		return "adminListChapterOnline";
	}

}
