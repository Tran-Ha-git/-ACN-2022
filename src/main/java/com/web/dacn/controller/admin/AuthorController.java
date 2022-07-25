package com.web.dacn.controller.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.web.dacn.dto.user.AuthorDTO;
import com.web.dacn.service.admin.AuthorService;

@Controller
@RequestMapping("/admin/authors")
public class AuthorController {
	@Autowired
	private AuthorService authorService;

	@GetMapping("")
	public ModelAndView authors(@RequestParam(required = false, defaultValue = "") String search,
			@RequestParam(required = false, defaultValue = "") String sort,
			@RequestParam(required = false, defaultValue = "1") int page) {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("adminListAuthor");

		String[] sortArr = sort.split("__");
		if (sortArr.length == 2) {
			modelAndView.addObject("sortName", sortArr[0]);
			modelAndView.addObject("sortType", sortArr[1]);
		}

		modelAndView.addObject("search", search);
		modelAndView.addObject("sort", sort);
		modelAndView.addObject("page", page);
		modelAndView.addObject("pageObj", authorService.getListAuthor(search, sort, page));
		return modelAndView;
	}
	
	@GetMapping("/new")
	public ModelAndView newAuthor() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("adminNewAuthor");
		return modelAndView;
	}
	
	@GetMapping("/update/{id}")
	@ExceptionHandler(Exception.class)
	public ModelAndView update(@PathVariable Long id, Exception ex) {
		ModelAndView modelAndView = new ModelAndView();
		if(ex == null) {			
			modelAndView.setViewName("404");
		} else {
			AuthorDTO authorDTO = authorService.findById(id);
			if(authorDTO != null) {
				modelAndView.addObject("author", authorDTO);
				modelAndView.setViewName("adminUpdateAuthor");
			} else {
				modelAndView.setViewName("404");				
			}
		}
		return modelAndView;
	}
}
