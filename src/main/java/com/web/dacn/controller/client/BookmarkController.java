package com.web.dacn.controller.client;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.web.dacn.dto.book.BookMarkDTO;
import com.web.dacn.entity.user.User;
import com.web.dacn.service.client.BookmarkService;

@Controller
@RequestMapping("/bookmark")
public class BookmarkController {
	@Autowired
	private HttpSession httpSession;

	@Autowired
	private BookmarkService bookmarkService;

	@GetMapping
	public ModelAndView showPage(@RequestParam(defaultValue = "all") String type,
			@RequestParam(defaultValue = "") String search, @RequestParam(defaultValue = "0") int page) {
		User user = (User) httpSession.getAttribute("user");
		if (user == null)
			return new ModelAndView("redirect:/");

		ModelAndView mav = new ModelAndView("bookmark");
		Page<BookMarkDTO> marks = bookmarkService.getBookmark(type, search, page);
		mav.addObject("marks", marks);
		return mav;
	}
}
