package com.web.dacn.utils;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.web.dacn.dto.book.BookDTO;
import com.web.dacn.dto.book.CommentBookDTO;
import com.web.dacn.dto.user.UserDto;
import com.web.dacn.entity.book.CommentBook;

@Component
public class CommentConverter {

	public CommentBookDTO toDTO(CommentBook entity) {
		CommentBookDTO comment = new CommentBookDTO();
		comment.setId(entity.getId());
		comment.setContent(entity.getContent());
		comment.setModTime(entity.getModTime());
		comment.setStatus(entity.getStatus());

		UserDto userdto = new UserDto();

		userdto.setId(entity.getUser().getId());
		userdto.setUsername(entity.getUser().getUsername());
		userdto.setEmail(entity.getUser().getEmail());
		BookDTO bookdto = new BookDTO();
		bookdto.setName(entity.getBook().getName());

		comment.setUser(userdto);
		comment.setBook(bookdto);
		return comment;

	}

	public CommentBookDTO commentToDTO(CommentBook entity) {

		CommentBookDTO comment = new CommentBookDTO();
		comment.setId(entity.getId());
		comment.setContent(entity.getContent());
		comment.setModTime(entity.getModTime());
		UserDto userdto = new UserDto();

		userdto.setId(entity.getUser().getId());
		userdto.setUsername(entity.getUser().getUsername());
		userdto.setEmail(entity.getUser().getEmail());

		BookDTO bookdto = new BookDTO();
		bookdto.setName(entity.getBook().getName());

		comment.setUser(userdto);
		comment.setBook(bookdto);

		List<CommentBookDTO> listComment = new ArrayList<CommentBookDTO>();
		for (CommentBook commentbook : entity.getCommentBooks()) {
			CommentBookDTO dto = toDTO(commentbook);
			listComment.add(dto);

		}

		comment.setCommentBooks(listComment);

		return comment;
	}
}
