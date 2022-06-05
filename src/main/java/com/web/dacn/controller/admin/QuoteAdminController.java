package com.web.dacn.controller.admin;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import javax.servlet.http.HttpSession;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.web.dacn.dto.quote.QuoteCategoryDto;
import com.web.dacn.entity.book.BookCategory;
import com.web.dacn.entity.quote.CommentQuote;
import com.web.dacn.entity.quote.Quote;
import com.web.dacn.entity.quote.ReviewQuote;
import com.web.dacn.entity.user.User;
import com.web.dacn.service.client.CommentQuoteService;
import com.web.dacn.service.client.QuoteCategoryService;
import com.web.dacn.service.client.QuoteService;
import com.web.dacn.service.client.ReviewQuoteService;

@Controller
@RequestMapping("/admin/quote")
public class QuoteAdminController {
	@Autowired
	private HttpSession session;
	
	@Autowired
	private QuoteService quoteService;
	
	@Autowired
	private QuoteCategoryService quoteCategoryService;
	
	@Autowired
	private ReviewQuoteService reviewQuoteService;
	
	@Autowired
	private CommentQuoteService commentQuoteService;
	
	@ModelAttribute("categories")
	public List<QuoteCategoryDto> getQuoteCategories() {
		return quoteCategoryService.findAll().stream().map((item) -> {
			QuoteCategoryDto quoteCategoryDto = new QuoteCategoryDto();
			BeanUtils.copyProperties(item, quoteCategoryDto);
			return quoteCategoryDto;
		}).collect(Collectors.toList());
	}
	
	@GetMapping("")
	public String get(@RequestParam(name = "q", required = false) String search, 
			@RequestParam(name = "category", required = false) String category,
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
		Page<Quote> results = null;
		if (StringUtils.hasText(category) && StringUtils.hasText(search)) {
			results = quoteService.findAllByContentContainingAndQuoteCategories_slug(search,category, pageable);
			model.addAttribute("search", search);
			model.addAttribute("category", category);
		}else if (StringUtils.hasText(search)) {
			results = quoteService.findByContentContaining(search, pageable);
			model.addAttribute("search", search);
		}else if (StringUtils.hasText(category)){
			results = quoteService.findAllByContentContainingAndQuoteCategories_slug("",category, pageable);
			model.addAttribute("category", category);
		}  else {
			results = quoteService.findAll(pageable);
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
		return "adminListQuote";
	}
	
	@GetMapping("/delete/{id}")
    public ModelAndView delete(@PathVariable("id") Long id, 
    		@RequestHeader(value = "referer", required = false) String referer) {
    	Optional<Quote> optional = quoteService.findById(id);
		if(optional.isPresent()) {
			quoteService.delete(optional.get());
		}
    	return new ModelAndView("redirect:" + referer);
    }
}
