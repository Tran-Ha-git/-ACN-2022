package com.web.dacn.controller.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.web.dacn.repository.AuthorRepository;
import com.web.dacn.repository.BookRepository;
import com.web.dacn.service.api.AuthorService;
import com.web.dacn.utils.ResponseObject;

@RestController
@RequestMapping("/api/authors")
public class AuthorAPI {
	@Autowired
	private AuthorService authorService;
	
	@Autowired
	private AuthorRepository authorRepository;
	
	@Autowired
	private BookRepository bookRepository;

	@DeleteMapping("")
	public ResponseEntity delete(@RequestBody Long[] ids) {
		ResponseObject response = new ResponseObject<>();
		try {
			authorService.deleteAllById(ids);
			response.setMessage("Successful!");
			response.setSuccess(true);	
			return ResponseEntity.ok(response);
		} catch (Exception ex) {
			ex.printStackTrace();
			response.setMessage("Unsuccessful");
			response.setSuccess(false);
			return ResponseEntity.status(HttpStatus.FORBIDDEN).body(response);
		}
	}
	
	@GetMapping("/{id}")
	public ResponseEntity get(@PathVariable Long id) {
		ResponseObject response = new ResponseObject<>();
		try {
			bookRepository.deleteById(id);
			response.setSuccess(true);
			return ResponseEntity.status(HttpStatus.OK).body(response);
		} catch(Exception e) {
			e.printStackTrace();
			response.setSuccess(false);
			return ResponseEntity.status(HttpStatus.FORBIDDEN).body(response);
		}
	}
}
