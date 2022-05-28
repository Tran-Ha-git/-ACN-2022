package com.web.dacn.service.admin.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.web.dacn.dto.book.BookDTO;
import com.web.dacn.dto.book.CommentBookDTO;
import com.web.dacn.dto.user.UserDto;
import com.web.dacn.entity.book.Book;
import com.web.dacn.entity.book.CommentBook;
import com.web.dacn.entity.user.User;
import com.web.dacn.repository.CommentBookRepository;
import com.web.dacn.service.admin.ICommentService;
import com.web.dacn.utils.CommentConverter;

@Service
public class CommentService implements ICommentService {
	private static int size = 20;
	@Autowired
	private CommentBookRepository commentBookRepository;
	@Autowired
	private CommentConverter commentConverter;

	@Override
	public Page<CommentBookDTO> getCommentsByPage(int page) {
		// TODO Auto-generated method stub
		Page<CommentBook> entities = commentBookRepository.findAll(getCommentsEntitesByPage(page));
		Page<CommentBookDTO> comments = entities.map(entity -> {
			CommentBookDTO dto = commentConverter.toDTO(entity);

			return dto;
		});
		return comments;

	}

	@Override
	public Page<CommentBookDTO> search(String userName, String bookName, int page) {
		// TODO Auto-generated method stub
		Pageable pageable = PageRequest.of(page, size);

		Page<CommentBook> entities = commentBookRepository.search(userName, bookName, pageable);

		Page<CommentBookDTO> comments = entities.map(entity -> {
			CommentBookDTO dto = commentConverter.toDTO(entity);

			return dto;
		});
		return comments;

	}
	// Get read format of book

	public Pageable getCommentsEntitesByPage(int page) {
		Pageable pageable = PageRequest.of(page, size);
		return pageable;
	}

	@Override
	public User getUser(long id) {
		// TODO Auto-generated method stub
		return commentBookRepository.findByUserId(id);
	}

	@Override
	public CommentBook spamComment(long id) {
		CommentBook comment = commentBookRepository.findById(id);
		comment.setStatus(0);
		commentBookRepository.save(comment);
		return comment;
	}

	@Override
	public CommentBook approvedComment(long id) {
		// TODO Auto-generated method stub
		CommentBook comment = commentBookRepository.findById(id);
		comment.setStatus(1);
		commentBookRepository.save(comment);
		return comment;

	}

	@Override
	public boolean deleteReviewBook(long id) {
		// TODO Auto-generated method stub
		commentBookRepository.deleteById(id);
		return false;
	}

}
