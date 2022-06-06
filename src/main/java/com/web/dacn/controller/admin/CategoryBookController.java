package com.web.dacn.controller.admin;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.web.dacn.dto.book.BookCategoryDTO;
import com.web.dacn.dto.user.UserDto;
import com.web.dacn.entity.book.BookCategory;
import com.web.dacn.service.admin.IBookCategoryService;

@Controller
@RequestMapping("/admin/book/category")
public class CategoryBookController {
	
	@Autowired
	private IBookCategoryService bookCategoryService;

    @GetMapping("")
    public String list(ModelMap model,
    		@RequestParam(name = "q", required = false) String search, 
			@RequestParam(name= "page") Optional<Integer> page, 
			@RequestParam(name= "size") Optional<Integer> size) {
    	int pageSize = size.orElse(9);
		model.addAttribute("size", pageSize);
		int currentPage = page.orElse(1);
		Pageable pageable;
		pageable = PageRequest.of(currentPage-1, pageSize, Sort.by("id").descending());
		
		Page<BookCategory> results ;
		if (StringUtils.hasText(search)) {
			results = bookCategoryService.findByNameContaining(search, pageable);
			model.addAttribute("search", search);
		} else {
			results = bookCategoryService.findAll(pageable);
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
		model.addAttribute("parents", bookCategoryService.findByParentBookCategoryIsNull());
		model.addAttribute("categoryDto", new BookCategoryDTO());
        return "adminListType";
    }
    
    @PostMapping("/create")
    public ModelAndView create(ModelMap model,  @Valid @ModelAttribute("categoryDto") 
    		BookCategoryDTO bookCategoryDTO, 
    		BindingResult result,
    		@RequestParam(name = "q", required = false) String search, 
			@RequestParam(name= "page") Optional<Integer> page, 
			@RequestParam(name= "size") Optional<Integer> size) {
    	List<BookCategory> books = null;
    	if(bookCategoryDTO.getSlug()!=null) {
    		books = bookCategoryService.findBySlug(bookCategoryDTO.getSlug());
    	}
    	if (result.hasErrors() || (books!=null && books.size()> 0)) {
    		result.addError(new FieldError("slug", "slug", "Slug already exists!"));
    		int pageSize = size.orElse(9);
    		model.addAttribute("size", pageSize);
    		int currentPage = page.orElse(1);
    		Pageable pageable;
    		pageable = PageRequest.of(currentPage-1, pageSize, Sort.by("id").descending());
    		
    		Page<BookCategory> results ;
    		if (StringUtils.hasText(search)) {
    			results = bookCategoryService.findByNameContaining(search, pageable);
    			model.addAttribute("search", search);
    		} else {
    			results = bookCategoryService.findAll(pageable);
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
    		model.addAttribute("parents", bookCategoryService.findByParentBookCategoryIsNull());
    		model.addAttribute("results", results);
			return new ModelAndView("adminListType");
		}
    	
    	
    	BookCategory bookCategory = new BookCategory();
    	BeanUtils.copyProperties(bookCategoryDTO, bookCategory);
    	bookCategoryService.save(bookCategory);
    	return new ModelAndView("redirect:/admin/book/category");
    }
    
    @PostMapping("/edit")
    public ModelAndView edit(ModelMap model,  @Valid @ModelAttribute("categoryDto") 
    		BookCategoryDTO bookCategoryDTO, 
    		BindingResult result,
    		@RequestParam(name = "q", required = false) String search, 
			@RequestParam(name= "page") Optional<Integer> page, 
			@RequestParam(name= "size") Optional<Integer> size) {
    	List<BookCategory> books = null;
    	if(bookCategoryDTO.getSlug()!=null) {
    		books = bookCategoryService.findBySlug(bookCategoryDTO.getSlug());
    	}
    	BookCategory bookCategory = bookCategoryService.getById(bookCategoryDTO.getId());
    	if (result.hasErrors() || (!bookCategory.getSlug().equalsIgnoreCase(bookCategoryDTO.getSlug()) && books!=null && books.size()> 0)) {
    		result.addError(new FieldError("slug", "slug", "Slug already exists!"));
    		int pageSize = size.orElse(9);
    		model.addAttribute("size", pageSize);
    		int currentPage = page.orElse(1);
    		Pageable pageable;
    		pageable = PageRequest.of(currentPage-1, pageSize, Sort.by("id").descending());
    		
    		Page<BookCategory> results ;
    		if (StringUtils.hasText(search)) {
    			results = bookCategoryService.findByNameContaining(search, pageable);
    			model.addAttribute("search", search);
    		} else {
    			results = bookCategoryService.findAll(pageable);
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
    		model.addAttribute("parents", bookCategoryService.findByParentBookCategoryIsNull());
    		model.addAttribute("results", results);
			return new ModelAndView("adminListType");
		}
    	
    	
    	
    	BeanUtils.copyProperties(bookCategoryDTO, bookCategory);
    	bookCategoryService.save(bookCategory);
    	return new ModelAndView("redirect:/admin/book/category");
    }
    
    @GetMapping("/delete/{id}")
    public ModelAndView delete(@PathVariable("id") Long id,
    		@RequestHeader(value = "referer", required = false) String referer) {
    	Optional<BookCategory> optional = bookCategoryService.findById(id);
		if(optional.isPresent()) {
			bookCategoryService.delete(optional.get());
		}
		return new ModelAndView("redirect:" + referer);
    }
    
}	