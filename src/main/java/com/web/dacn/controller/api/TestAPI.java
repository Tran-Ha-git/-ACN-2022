package com.web.dacn.controller.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.web.dacn.repository.AuthorRepository;
import com.web.dacn.repository.BookRepository;
import com.web.dacn.repository.UserRepository;
import com.web.dacn.utils.ResponseObject;

@RestController
@RequestMapping("/test")
public class TestAPI {	
	@Autowired
	private AuthorRepository authorRepository;
	
	@GetMapping("")
	public ResponseEntity a(@RequestParam Long id, @RequestParam String slug) {
		ResponseObject response = new ResponseObject<>();
		try {
			response.setData(authorRepository.existsByIdNotAndSlug(id, slug));
			response.setSuccess(true);
		} catch (Exception e) {
			e.printStackTrace();
			response.setSuccess(false);
		}
		return ResponseEntity.ok(response);
	}	
}