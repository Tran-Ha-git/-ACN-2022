package com.web.dacn.controller.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
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

	/*
	 * audio
	 */
	@GetMapping("/{bookSlug}/audio/{id}")
	public Boolean checkAudioMark(@PathVariable String bookSlug, @PathVariable Long id) {
		return bookmarkService.checkAudioMark(bookSlug, id);
	}

	@PostMapping("/{bookSlug}/audio/{id}")
	public Boolean addAudioMarkByName(@PathVariable String bookSlug, @PathVariable Long id) {
		return bookmarkService.addAudioMark(bookSlug, id);
	}

	@DeleteMapping("/{bookSlug}/audio/id-{id}")
	public Boolean deleteAudioMark(@PathVariable String bookSlug, @PathVariable Long id) {
		return bookmarkService.deleteAudioMark(bookSlug, id);
	}

	/*
	 * online
	 */
	@GetMapping("/{bookSlug}/online/{chapter}")
	public Boolean checkOnlineMark(@PathVariable String bookSlug, @PathVariable String chapter) {
		return bookmarkService.checkOnlineMark(bookSlug, chapter);
	}

	@PostMapping("/{bookSlug}/online/{chapter}")
	public Boolean addOnlineMark(@PathVariable String bookSlug, @PathVariable String chapter) {
		return bookmarkService.addOnlineMark(bookSlug, chapter);
	}

	@DeleteMapping("/{bookSlug}/online/{chapter}")
	public Boolean deleteOnlineMark(@PathVariable String bookSlug, @PathVariable String chapter) {
		return bookmarkService.deleteOnlineMark(bookSlug, chapter);
	}

	@DeleteMapping("/{bookSlug}/online/id-{id}")
	public Boolean deleteOnlineMarkById(@PathVariable String bookSlug, @PathVariable Long id) {
		return bookmarkService.deleteOnlineMarkById(bookSlug, id);
	}
	
	/*
	 * pdf
	 */
	@GetMapping("/{bookSlug}/pdf/{id}")
	public Boolean checkPdfMark(@PathVariable String bookSlug, @PathVariable Long id) {
		return bookmarkService.checkPdfMark(bookSlug, id);
	}

	@PostMapping("/{bookSlug}/pdf/{id}")
	public Boolean addPdfMark(@PathVariable String bookSlug, @PathVariable Long id) {
		return bookmarkService.addPdfMark(bookSlug, id);
	}

	@DeleteMapping("/{bookSlug}/pdf/id-{id}")
	public Boolean deletePdfMarkById(@PathVariable String bookSlug, @PathVariable Long id) {
		return bookmarkService.deletePdfMarkById(bookSlug, id);
	}
}
