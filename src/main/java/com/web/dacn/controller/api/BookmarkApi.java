package com.web.dacn.controller.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.web.dacn.service.client.BookmarkService;

@RestController
@RequestMapping("/api/mark")
public class BookmarkApi {
	
	@Autowired
	private BookmarkService bookmarkService;
	
	@PostMapping("/{bookSlug}/audio/{id}")
	public Boolean addAudioMark(@PathVariable String bookSlug, @PathVariable Long id) {
		return bookmarkService.addAudioMark(bookSlug,id);
	}
}
