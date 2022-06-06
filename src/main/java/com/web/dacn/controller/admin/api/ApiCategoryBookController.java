package com.web.dacn.controller.admin.api;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.web.dacn.dto.book.BookCategoryDTO;
import com.web.dacn.entity.book.BookCategory;
import com.web.dacn.service.admin.IBookCategoryService;

@RestController
@CrossOrigin(origins="/*")
@RequestMapping("/api/v1/book/category")
public class ApiCategoryBookController {
	@Autowired
	private IBookCategoryService bookCategoryService;
	
	@GetMapping("/{id}")
	public BookCategory detail(@PathVariable("id") Long id) {
		Optional<BookCategory> optional = bookCategoryService.findById(id);
		return optional.get();
	}
	
	@PostMapping("/edit")
	public BookCategory edit(BookCategoryDTO bookCategoryDTO) {
		Optional<BookCategory> optional = bookCategoryService.findById(bookCategoryDTO.getId());
		List<BookCategory> bookCategories = bookCategoryService.findBySlug(bookCategoryDTO.getSlug());
		if(!optional.get().getSlug().equalsIgnoreCase(bookCategoryDTO.getSlug()) && bookCategories!=null && bookCategories.size()>0) {
			return null;
		} else if(optional.isPresent()) {
			BookCategory bookCategory = optional.get();
			bookCategory.setName(bookCategoryDTO.getName());
			bookCategory.setSlug(bookCategoryDTO.getSlug());
			bookCategory.setStatus(bookCategoryDTO.getStatus());
			bookCategory.setMetaTitle(bookCategoryDTO.getMetaTitle());
			bookCategory.setMetaDescription(bookCategoryDTO.getMetaDescription());
			bookCategoryService.save(bookCategory);
			return optional.get();
		}else {
			return null;
		}
	}
	
}
