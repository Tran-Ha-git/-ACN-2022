package com.web.dacn.controller.admin;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.web.dacn.entity.quote.CommentQuote;
import com.web.dacn.entity.quote.Quote;
import com.web.dacn.service.client.CommentQuoteService;

@Controller
@RequestMapping("/admin/quote/comments")
public class CommenQuoteController {

	@Autowired
	private CommentQuoteService commentQuoteService;
	
	@GetMapping("")
	public String get(@RequestParam(name = "q", required = false) String search, 
			@RequestParam(name = "sort", required = false) String sort,
			ModelMap model,
			@RequestParam(name= "page") Optional<Integer> page, 
			@RequestParam(name= "size") Optional<Integer> size) {
		int pageSize = size.orElse(9);
		model.addAttribute("size", pageSize);
		int currentPage = page.orElse(1);
		Pageable pageable;
		if(StringUtils.hasText(sort)) {
			String field = sort.trim().split("_")[0];
			String type =  sort.trim().split("_")[1];
			if(type.contentEquals("desc")) {
				pageable = PageRequest.of(currentPage-1, pageSize, Sort.by(field).descending());
			}else {
				pageable = PageRequest.of(currentPage-1, pageSize, Sort.by(field).ascending());
			}
			model.addAttribute("sort", sort);
		}else {
			pageable = PageRequest.of(currentPage-1, pageSize, Sort.by("id").descending());
		}
		Page<CommentQuote> results = null;
		if(StringUtils.hasText(search)) {
			results = commentQuoteService.findByUserUsernameOrUserEmailOrUserFullnameContaining(search, search, search, pageable);
		}else {
			results = commentQuoteService.findAll(pageable);
		}
		int totalPages = results.getTotalPages();
		if (totalPages > 0) {
			int start = Math.max(1, currentPage-2);
			int end = Math.min(currentPage + 2, totalPages);
			if (totalPages > 5) {
				if (end == totalPages)
					start = end - 5;
				else if (start == 1)
					end = start + 5;
			}
			List<Integer> pages = IntStream.rangeClosed(start, end).boxed().collect(Collectors.toList());
			model.addAttribute("pages", pages);
		} 
		model.addAttribute("results", results);
		return "adminCommentQuote";
	}
	
	
	@GetMapping("/delete/{id}")
    public ModelAndView delete(@PathVariable("id") Long id, 
    		@RequestHeader(value = "referer", required = false) String referer) {
    	Optional<CommentQuote> optional = commentQuoteService.findById(id);
		if(optional.isPresent()) {
			commentQuoteService.delete(optional.get());
		}
    	return new ModelAndView("redirect:" + referer);
    }

}
