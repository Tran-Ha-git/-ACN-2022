package com.web.dacn.controller.client;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.web.dacn.dto.book.BookDTO;
import com.web.dacn.dto.quote.QuoteDto;
import com.web.dacn.service.client.HomeService;

@Controller
@RequestMapping(value = {"/", "/home"})
public class HomeController {
	
	@Autowired
	private HomeService homeService;
	
	@ModelAttribute("suggest_books")
	private List<BookDTO> getSuggestBook() {
		return homeService.getSuggestBook();
	}
	
	@ModelAttribute("top_audios")
	private List<BookDTO> getTopAudioBook() {
		return homeService.getAudioBookByTopView();
	}

	@ModelAttribute("top_ebooks")
	private List<BookDTO> getTopEbookBook() {
		return homeService.getEbookBookByTopView();
	}
	
	@ModelAttribute("top_newbooks")
	private List<BookDTO> getTopNewBook() {
		return homeService.getNewBooks();
	}
	
	@ModelAttribute("top_mangas")
	private List<BookDTO> getMangaBook() {
		return homeService.getMangaBookByTopView();
	}
	
	@ModelAttribute("top_quotes")
	private List<QuoteDto> getTopQuote() {
		return homeService.getQuoteByTopView();
	}
	
	@GetMapping
	public ModelAndView get() {
		ModelAndView modelAndView = new ModelAndView();
//		modelAndView.addObject("top_quotes", homeService.getQuoteByTopView());
//		modelAndView.addObject("top_mangas", homeService.getMangaBookByTopView());
//		modelAndView.addObject("top_newbooks", homeService.getNewBooks());
//		modelAndView.addObject("top_newbooks", homeService.getNewBooks());
//		modelAndView.addObject("top_ebooks", homeService.getEbookBookByTopView());
//		modelAndView.addObject("top_audios", homeService.getAudioBookByTopView());
//		modelAndView.addObject("suggest_books", homeService.getSuggestBook());
		modelAndView.setViewName("homePage");
		return modelAndView;
	}
}
