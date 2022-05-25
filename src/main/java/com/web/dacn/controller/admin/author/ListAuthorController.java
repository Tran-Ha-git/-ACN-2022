package com.web.dacn.controller.admin.author;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.web.dacn.service.admin.ListAuthorService;

@Controller
@RequestMapping("/admin/authors")
public class ListAuthorController {
	@Autowired
	private ListAuthorService listAuthorService;
	
	@GetMapping("")
	public ModelAndView authors(@RequestParam(required = false, defaultValue = "") String search, @RequestParam(required = false, defaultValue = "") String sort, @RequestParam(required = false, defaultValue = "0") int page) {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("adminListAuthor");

		String[] sortArr = sort.split("__");
		if(sortArr.length == 2) {
			modelAndView.addObject("sortName", sortArr[0]);
			modelAndView.addObject("sortType", sortArr[1]);
		}
		
		modelAndView.addObject("search", search);
		modelAndView.addObject("sort", sort);
		modelAndView.addObject("page", page);
		modelAndView.addObject("pageObj", listAuthorService.getListAuthor(search, sort, page));
		return modelAndView;
	}
}
