package com.web.dacn.service.client;

import java.util.List;

import org.springframework.data.domain.Page;

import com.web.dacn.dto.book.BookCategoryDTO;
import com.web.dacn.dto.book.BookDTO;
import com.web.dacn.dto.book.CommentBookDTO;
import com.web.dacn.dto.user.AuthorDTO;
import com.web.dacn.entity.user.User;

public interface DetailBookService {
	BookDTO getDetailBookBySlug(String slug);

	List<BookDTO> getSimilarBooksByBookSlug(String slug, List<BookCategoryDTO> list, List<AuthorDTO> list2);

	long getTotalReviews();

	float averageReviewsBook(String slug);

	Page<CommentBookDTO> getCommentsByBookSlug(String slug, int page);

	User getLoggedInUser();

	CommentBookDTO createComment(String slug, int star, String content);

	boolean hadReviewBook(String slug);

	List<CommentBookDTO> getCommentBooksByBookSlug(String slug, Integer page);

	CommentBookDTO createResponse(String slug, Long commentId, String content);

	Boolean addLovebook(String slug);

	Boolean deleteLovebook(String slug);

	Boolean checkLovebook(String slug);

	Boolean deleteLovebook(Long[] ids);
}
