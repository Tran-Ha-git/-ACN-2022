package com.web.dacn.controller.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.web.dacn.service.admin.ListAuthorService;

@RestController
@RequestMapping("/test")
public class TestAPI {	
	@Autowired
	private ListAuthorService listAuthorService;
	
	@GetMapping("")
	public ResponseEntity get(@RequestParam(required = false) String search, @RequestParam(required = false) String sort, @RequestParam(required = false, defaultValue = "0") int page) {
		if(search == null) search = "";
		if(sort == null) sort = "";
		return ResponseEntity.ok(listAuthorService.getListAuthor(search, sort, page));
	}	
}