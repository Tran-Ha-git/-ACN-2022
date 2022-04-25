package com.web.dacn.controller.quote;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.web.dacn.dto.quote.QuoteCategoryDto;
import com.web.dacn.entity.quote.Quote;
import com.web.dacn.service.quote.QuoteCategoryService;
import com.web.dacn.service.quote.QuoteService;

@Controller
@RequestMapping("quotes")
public class QuoteController {
	@Autowired
	private QuoteService quoteService;
	
	@Autowired
	private QuoteCategoryService quoteCategoryService;
	
	@ModelAttribute("categories")
	public List<QuoteCategoryDto> getQuoteCategories() {
		return quoteCategoryService.findAll().stream().map((item) -> {
			QuoteCategoryDto quoteCategoryDto = new QuoteCategoryDto();
			BeanUtils.copyProperties(item, quoteCategoryDto);
			return quoteCategoryDto;
		}).toList();
	}
	
	@GetMapping("")
	public String get(@RequestParam(name = "q", required = false) String search, ModelMap model,
			@RequestParam(name= "page") Optional<Integer> page, 
			@RequestParam(name= "size") Optional<Integer> size) {
		int pageSize = size.orElse(9	);
		model.addAttribute("size", pageSize);
		int currentPage = page.orElse(1);
		Pageable pageable = PageRequest.of(currentPage-1, pageSize, Sort.by("id"));
		Page<Quote> results = null;
		if (StringUtils.hasText(search)) {
			results = quoteService.findByContentContaining(search, pageable);
			model.addAttribute("search", search);
		} else {
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
		return "listQuotePage";
	}
}
