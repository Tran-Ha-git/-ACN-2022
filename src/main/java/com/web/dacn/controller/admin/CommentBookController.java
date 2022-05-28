package com.web.dacn.controller.admin;

import java.util.ArrayList;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.web.dacn.dto.book.BookDTO;
import com.web.dacn.dto.book.CommentBookDTO;
import com.web.dacn.entity.user.User;
import com.web.dacn.service.admin.IBookService;
import com.web.dacn.service.admin.ICommentService;

@Controller
@RequestMapping("/admin")
public class CommentBookController {
	@Autowired
	ICommentService commentService;

	@RequestMapping("/comments")
	public String getCommentsByPage(Model model, HttpSession session, HttpServletRequest req) {

		return getCommentsByPage(model, session, 0, req);
	}

	@RequestMapping("/comments/{page}")
	public String getCommentsByPage(Model model, HttpSession session, @PathVariable("page") int currentpage,
			HttpServletRequest req) {

		Page<CommentBookDTO> commentsInPage = commentService.getCommentsByPage(currentpage);

		List<CommentBookDTO> comments = commentsInPage.getContent();

		List<Integer> totalPages = new ArrayList<Integer>();
		for (int i = 0; i < commentsInPage.getTotalPages(); i++) {
			totalPages.add(i);
		}
         
//		for (CommentBookDTO commentBook : comments) {
//			User user = commentService.getUser(commentBook.getUser().getId());
//			model.addAttribute("user", user);
//		}
		model.addAttribute("totalPages", totalPages);
		model.addAttribute("total", commentsInPage.getTotalElements());
		model.addAttribute("comments", comments);
      model.addAttribute("id", comments.get(0).getUser().getId());
		model.addAttribute("currentpage", currentpage);

		return "adminListComment";
	}
	@PostMapping("searchComment")
	public String search(Model model, HttpSession session, HttpServletRequest req) {
		return searchByPage(model, 0, session, req);
	}

	@RequestMapping("searchComment/{page}")
	public String searchByPage(Model model, @PathVariable("page") int currentpage, HttpSession session,
			HttpServletRequest req) {
		String bookName, userName, message = "";
		Page<CommentBookDTO> commentsInPage;
		List<CommentBookDTO> results = new ArrayList<CommentBookDTO>();
		List<Integer> totalPages = new ArrayList<Integer>();
		

		if ((currentpage > 0) || (currentpage == 0 && !req.getMethod().equalsIgnoreCase("POST"))) {

			bookName = (String) session.getAttribute("bookName");
			userName = (String) session.getAttribute("userName");

		} else {

			bookName = req.getParameter("bookname");
			userName = req.getParameter("username");

			session.setAttribute("bookName", bookName);
			session.setAttribute("userName", userName);

		}

		commentsInPage = commentService.search(bookName, userName, currentpage);
		results = commentsInPage.getContent();

		

		if (results.size() <= 0) {
			message = "No result";
			model.addAttribute("message", message);
		}
		for (int i = 0; i < commentsInPage.getTotalPages(); i++) {
			totalPages.add(i);
		}

		model.addAttribute("searchPages", totalPages);
		
		model.addAttribute("total", commentsInPage.getTotalElements());
		model.addAttribute("comments", results);

		return "adminListComment";
	}

	@RequestMapping("/spamComment")
	public String spamComment(@RequestParam("id") long id, Model model, HttpServletRequest request) {
		commentService.spamComment(id);
		return "redirect:/admin/comments";
	}

	@RequestMapping("/approvedComment")
	public String approvedComment(@RequestParam("id") long id, Model model, HttpServletRequest request) {
		commentService.approvedComment(id);
		return "redirect:/admin/comments";
	}

	@RequestMapping(value = "/DeleteCommentBook")
	public String deleteComment(@RequestParam(name = "id") long id, ModelMap model, HttpSession session) {

		commentService.deleteReviewBook(id);

		return "redirect:/admin/comments";
	}

	@RequestMapping("/DeleteAllComment")
	public String deleteAllComment(Model model, HttpServletRequest request) {
		String[] deleteItems = request.getParameterValues("delete-item");
		for (String item : deleteItems) {
			long id = Long.parseLong(item);
			commentService.deleteReviewBook(id);
		}
		return "redirect:/admin/comments";
	}

}
