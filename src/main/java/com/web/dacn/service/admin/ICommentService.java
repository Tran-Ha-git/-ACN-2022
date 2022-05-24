package com.web.dacn.service.admin;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import com.web.dacn.dto.book.BookDTO;
import com.web.dacn.dto.book.CommentBookDTO;
import com.web.dacn.dto.user.UserDto;
import com.web.dacn.entity.book.CommentBook;
import com.web.dacn.entity.user.User;
@Service
public interface ICommentService {
	Page<CommentBookDTO> getCommentsByPage(int page);

	Page<CommentBookDTO> search(String bookName, String userName, int page);

	User getUser(long id);
	CommentBook spamComment(long id);
	CommentBook approvedComment(long id);
	  boolean deleteReviewBook(long id);
	 
	

}
