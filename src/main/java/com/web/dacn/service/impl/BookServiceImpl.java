package com.web.dacn.service.impl;

import java.text.ParseException;

import java.util.ArrayList;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.web.dacn.dto.BookDTo;
import com.web.dacn.dto.BooksDTO;
import com.web.dacn.entity.AuthorEntity;

import com.web.dacn.entity.BookEntity;

import com.web.dacn.repository.AuthorRepository;

import com.web.dacn.repository.BookRepository;
import com.web.dacn.service.BookService;

@Service
public class BookServiceImpl implements BookService {
	@Autowired
	private BookRepository repository;
	@Autowired
	private AuthorRepository authorRepository;

	

	@Override
	@Transactional
	public boolean deleteBook(int id) {
		// TODO Auto-generated method stub
		repository.deleteById(id);
		return false;

	}

//	@Override
//	public List<BookDTo> findAll() {
//		// TODO Auto-generated method stub
//		return null;
//	}
	

	@Override
	@Transactional
	public boolean deleteByIdPdf(int id) {
		// TODO Auto-generated method stub
		repository.deleteByIdPdf(id);
		return false;

	}

	@Override
	@Transactional
	public boolean deleteByIdAudio(int id) {
		// TODO Auto-generated method stub
		repository.deleteByIdAudio(id);
		return false;

	}

	@Override
	@Transactional
	public boolean deleteByIdOnline(int id) {
		// TODO Auto-generated method stub
		repository.deleteByIdOnline(id);
		return false;

	}

	@Override
	@Transactional
	public boolean deleteByIdBookAuthor(int id) {
		// TODO Auto-generated method stub
		repository.deleteByIdBookAuthor(id);
		return false;

	}

	@Override
	@Transactional
	public boolean deleteByIdBookBookCategory(int id) {
		// TODO Auto-generated method stub
		repository.deleteByIdBookBookCategory(id);
		return false;

	}

	// Get books
	@Override
	public List<BooksDTO> getBooks() {
		List<BooksDTO> books = new ArrayList<BooksDTO>();
		List<BookEntity> entities = repository.findAll();
		for (BookEntity entity : entities) {
			BooksDTO dto = new BooksDTO(entity.getId(), entity.getName(), entity.getThumbnail(), entity.getStatus(),
					entity.getMod_time());

			books.add(dto);
		}
		return books;

	}

	// Get authors of book by book's id
	@Override
	public List<String> getAuthorsOfBook(int id) {
		return repository.getAuthorsOfBook(id);

	}

	// Get categories of book by book's id
	@Override
	public List<String> getCategoriesOfBook(int id) {
		return repository.getCategoriesOfBook(id);
	}

	@Override
	public boolean checkAvailable(String name) {
		// TODO Auto-generated method stub
		if (name != null) {
			return true;
		}

		return false;
	}

	@Override
	public int convertStatus(String status) {
		// TODO Auto-generated method stub
		switch (status) {
		case "Đã xóa":
			return 0;

		case "Hoàn thành":
			return 1;

		case "Đang update":
			return 2;

		}
		return 0;
	}

	@Override
	public BookDTo findById(int id) {
		BookEntity entity = repository.findById(id);
		AuthorEntity authorEnity = authorRepository.findById(id);

		// TODO Auto-generated method stub
		BookDTo dto = new BookDTo(entity.getName(), entity.getThumbnail(), entity.getPrice(), entity.isVip(),
				entity.getDescription(), entity.getSlug(), entity.getMetaTitle(), entity.getMetaDescription(),
				entity.getStatus());
		return dto;

	}

	@Override
	public List<String> getPdfOfBook(int id) {
		// TODO Auto-generated method stub
		return repository.getPdfOfBook(id);
	}

	@Override
	public List<BookDTo> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<String> getOnlineOfBook(int id) {
		// TODO Auto-generated method stub
		return repository.getOnlineOfBook(id);
		
	}

	@Override
	public List<String> getAudioOfBook(int id) {
		// TODO Auto-generated method stub
		return repository.getAudioOfBook(id);
	}



}
