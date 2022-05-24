package com.web.dacn.controller.client;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.web.dacn.dto.book.BookCategoryDTO;
import com.web.dacn.dto.book.BookDTO;
import com.web.dacn.dto.book.CommentBookDTO;
import com.web.dacn.service.client.DetailBookService;
import com.web.dacn.service.client.ListBookService;

@Controller
@RequestMapping("/books")
public class ListBookController {
	
	@Autowired
	private ListBookService listBookService;
	
	@Autowired
	private DetailBookService detailBookService;
	
	@ModelAttribute("categories")
	private List<BookCategoryDTO> getCategories() {
		return listBookService.getBookCategories();
	}

	@ModelAttribute("top_audios")
	private List<BookDTO> getTopAudioBook() {
		return listBookService.getTopAudioBooks();
	}

	@ModelAttribute("top_onlines")
	private List<BookDTO> getTopOnlineBook() {
		return listBookService.getTopOnlineBooks();
	}
	
	@GetMapping
	public ModelAndView getBook(
			@RequestParam(required = false) String type, 
			@RequestParam(required = false) String search, 
			@RequestParam(required = false) String sort, 
			@RequestParam(required = false, defaultValue = "0") int page, 
			@RequestParam(required = false,name = "category_id") Long categoryId)  {
		ModelAndView modelAndView = new ModelAndView("listBookPage");
		
		if(search == null) search = "";
		if(sort == null) sort = "";
		if(!"ebook".equals(type) && !"audio".equals(type) && !"pdf".equals(type)) type = "all";
		
		Page<BookDTO> pageObj = listBookService.getPageBook(type, search, sort, page, categoryId);
		
		modelAndView.addObject("pageObj", pageObj);
		modelAndView.addObject("search", search);
		modelAndView.addObject("category_id", categoryId);
		modelAndView.addObject("type", type);
		
		return modelAndView;
	}

	@GetMapping("/{slug}")
	public ModelAndView getDetailBookBySlug(@PathVariable String slug) {
		BookDTO bookDTO = detailBookService.getDetailBookBySlug(slug);

		if (bookDTO == null)
			return new ModelAndView("404");

		ModelAndView mav = new ModelAndView("detailBookPage");
		mav.addObject("bookDTO", bookDTO);

		List<BookDTO> similarBooks = detailBookService.getSimilarBooksByBookSlug(slug, bookDTO.getCategories(),
				bookDTO.getAuthors());
		mav.addObject("similarBooks", similarBooks);

		float score = detailBookService.averageReviewsBook(slug);
		mav.addObject("score", score);

		long totalReviews = detailBookService.getTotalReviews();
		mav.addObject("totalReviews", totalReviews);

		Page<CommentBookDTO> comments = detailBookService.getCommentsByBookSlug(slug, 0);
		if (comments != null) {
			mav.addObject("comments", comments);
		}

		return mav;
	}
}
