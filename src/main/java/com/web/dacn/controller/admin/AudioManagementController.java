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
public class AudioManagementController {
	@Autowired
	private BookService bookService;

	@Autowired
	private IAudioService audioService;
	@Autowired
	private UploadFile uploadFile;

	
	@RequestMapping(value = "/editBook/listAudio")
	public String getChapterAudio(ModelMap model, HttpSession session, HttpServletRequest request)
			throws ParseException {
		String id = request.getParameter("id");
		long bId = Long.parseLong(id);

		BookDTO bookDto = bookService.findById(bId);
		model.addAttribute("book", bookDto);
		session.setAttribute("id", id);
		return "adminListChapterAudio";

	}

	@RequestMapping(value = "/addChapterAudio")
	public String addChapterAudio(@ModelAttribute Audio audio, ModelMap model, HttpSession session,
			HttpServletRequest request, @RequestParam("urlAudio") MultipartFile file)
			throws ParseException, IOException {

		String sid = (String) session.getAttribute("id");
		long id = Long.parseLong(sid);

		String url = uploadFile.upload(file, request, "/assets/audios/");

		Audio newAudio = new Audio();

		newAudio.setName(audio.getName());
		newAudio.setPriority(audio.getPriority());
		newAudio.setUrl(url);
		Book book = bookService.getBookById(id);
		newAudio.setBook(book);

		audioService.save(newAudio);
		model.addAttribute("book", book);
		return "adminListChapterAudio";

	}

	@RequestMapping(value = "/getEditAudioChapter/{id}/{bookid}")
	public String getEditChapter(@PathVariable("id") long id, @PathVariable("bookid") long bookId, Model model,
			HttpSession session) {
		Audio audio = audioService.findAudioById(id);
		model.addAttribute("currentAudio", audio);
		BookDTO book = bookService.findById(bookId);
		model.addAttribute("book", book);

		return "adminListChapterAudio";
	}

	@RequestMapping(value = "/editChapterAudio/{id}")
	public String editChapterAudio(@PathVariable("id") long id, @ModelAttribute Audio audio,
			@RequestParam("urlAudio") MultipartFile file, ModelMap model,
			HttpServletRequest request) throws ParseException, IOException {

		Audio newAudio = audioService.findAudioById(id);

		newAudio.setName(audio.getName());
		newAudio.setPriority(audio.getPriority());
		if (!file.isEmpty()) {
			String url = uploadFile.upload(file, request, "/assets/audios/");
			newAudio.setUrl(url);
		}

		Book book = bookService.getBookById(newAudio.getBook().getId());
		audioService.updateAudio(id, newAudio);
		model.addAttribute("book", book);

		return "adminListChapterAudio";

	}

	
	@RequestMapping(value = "/DeleteChapterAudio")
	public String deleteAudio(@RequestParam(name = "id") String aid, @RequestParam(name = "bookid") String bookid,
			ModelMap model, HttpSession session) {

		long audioId = Long.parseLong(aid);
		long bookId = Long.parseLong(bookid);
		audioService.deleteAudio(audioId);

		BookDTO book = bookService.findById(bookId);
		model.addAttribute("book", book);

		return "adminListChapterAudio";
	}
}
