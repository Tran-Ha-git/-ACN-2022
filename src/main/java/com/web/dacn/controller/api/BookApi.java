package com.web.dacn.controller.api;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.domain.Sort.Order;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.web.dacn.repository.BookRepository;
import com.web.dacn.repository.QuoteRepository;
import com.web.dacn.service.client.HomeService;
import com.web.dacn.service.client.ListBookService;
import com.web.dacn.service.client.QuoteService;

@RestController
@RequestMapping("/test")
public class BookApi {	
	@Autowired
	private HomeService homeService;
	
	@GetMapping("")
	public ResponseEntity get() {
		return ResponseEntity.ok(homeService.getAudioBookByTopView());
	}	
}