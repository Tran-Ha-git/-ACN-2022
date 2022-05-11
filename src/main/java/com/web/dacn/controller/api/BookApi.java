package com.web.dacn.controller.api;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.web.dacn.repository.BookRepository;
import com.web.dacn.service.client.ListBookService;

@RestController(value = "/test")
public class BookApi {
	@Autowired
	private BookRepository bookRepository;
	
	@Autowired
	private ListBookService listBookService;
	
	private ModelMapper mapper = new ModelMapper();
	
	@GetMapping("")
	public ResponseEntity get(			
			@RequestParam(required = true) String type, 
			@RequestParam(required = false) String search, 
			@RequestParam(required = false) String sort, 
			@RequestParam(required = false, defaultValue = "0") Integer page,
			@RequestParam(name = "category_id", required = false) Long categoryId) {
		return ResponseEntity.ok(listBookService.getPageBook(type, search, sort, page, categoryId));
	}
	
	@GetMapping("/a")
	public ResponseEntity geta() {
		return ResponseEntity.ok(listBookService.getTopAudioBooks());
	}
	
}