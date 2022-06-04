package com.web.dacn.controller.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.web.dacn.dto.book.CommentBookDTO;
import com.web.dacn.service.client.DetailBookService;

import io.swagger.v3.oas.annotations.parameters.RequestBody;

@RestController
@RequestMapping("/api/book")
public class DetailBookApi {
	@Autowired
	private DetailBookService detailBookService;

	@GetMapping("/checkLogin")
	public ResponseEntity<Boolean> checkLogin() {
		return ResponseEntity.ok(detailBookService.getLoggedInUser() == null ? false : true);
	}

	@PostMapping("/{slug}/comment")
	public CommentBookDTO createComment(@PathVariable String slug, @RequestParam int star,
			@RequestParam String content) {
		return detailBookService.createComment(slug, star, content);
	}

	@GetMapping("/{slug}/checkReview")
	public ResponseEntity<Boolean> checkReview(@PathVariable String slug) {
		return ResponseEntity.ok(detailBookService.hadReviewBook(slug));
	}

	@GetMapping("/{slug}/more-review/{page}")
	public List<CommentBookDTO> getMoreReviews(@PathVariable Integer page, @PathVariable String slug) {
		return detailBookService.getCommentBooksByBookSlug(slug, page);
	}

	@PostMapping("/{slug}/response/{commentId}")
	public CommentBookDTO createComment(@PathVariable String slug, @PathVariable Long commentId,
			@RequestParam String content) {
		return detailBookService.createResponse(slug, commentId, content);
	}
	
	@PostMapping("/{slug}/love")
	public Boolean addLovebook(@PathVariable String slug) {
		return detailBookService.addLovebook(slug);
	}
	
	@DeleteMapping("/{slug}/love")
	public Boolean deleteLovebook(@PathVariable String slug) {
		return detailBookService.deleteLovebook(slug);
	}
	
	@GetMapping("/{slug}/love")
	public Boolean checkLovebook(@PathVariable String slug) {
		return detailBookService.checkLovebook(slug);
	}
	
	@DeleteMapping("/love/books")
	public Boolean checkLovebook( @RequestParam Long[] ids) {
		return detailBookService.deleteLovebook(ids);
	}
}
