package com.web.dacn.service.client.impl;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

import javax.servlet.http.HttpSession;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.web.dacn.dto.book.AudioDTO;
import com.web.dacn.dto.book.BookCategoryDTO;
import com.web.dacn.dto.book.BookDTO;
import com.web.dacn.dto.book.BookMarkDTO;
import com.web.dacn.dto.book.OnlineDTO;
import com.web.dacn.dto.book.PdfDTO;
import com.web.dacn.dto.user.AuthorDTO;
import com.web.dacn.dto.user.UserDto;
import com.web.dacn.entity.book.Audio;
import com.web.dacn.entity.book.Book;
import com.web.dacn.entity.book.BookCategory;
import com.web.dacn.entity.book.BookMark;
import com.web.dacn.entity.book.Online;
import com.web.dacn.entity.book.Pdf;
import com.web.dacn.entity.user.User;
import com.web.dacn.repository.AudioRepository;
import com.web.dacn.repository.BookMarkRepository;
import com.web.dacn.repository.BookRepository;
import com.web.dacn.repository.OnlineRepository;
import com.web.dacn.repository.PdfRepository;
import com.web.dacn.service.client.BookmarkService;

@Service
public class BookmarkServiceImpl implements BookmarkService {
	@Autowired
	private HttpSession session;

	@Autowired
	private BookRepository bookRepository;

	@Autowired
	private AudioRepository audioRepository;

	@Autowired
	private OnlineRepository onlineRepository;

	@Autowired
	private PdfRepository pdfRepository;

	@Autowired
	private BookMarkRepository bookMarkRepository;

	/*
	 * audio
	 */
	@Transactional
	@Override
	public Boolean addAudioMark(String bookSlug, Long id) {
		User user = getLoggedInUser();
		Book book = bookRepository.findOneBySlug(bookSlug).orElse(null);
		if (user == null || book == null)
			return false;

		if (!bookMarkRepository.existsByUser_IdAndBook_SlugAndAudio_Id(user.getId(), bookSlug, id)) {
			BookMark bookMark = new BookMark();
			bookMark.setBook(book);
			bookMark.setUser(user);
			Optional<Audio> audio = audioRepository.findById(id);
			if(audio.isPresent()) {
				bookMark.setAudio(audio.get());
				bookMarkRepository.save(bookMark);
			}else {
				return false;
			}
		}
		return true;
	}


	@Override
	public Boolean checkAudioMark(String bookSlug, Long id) {
		User user = getLoggedInUser();
		Book book = bookRepository.findOneBySlug(bookSlug).orElse(null);
		if (user == null || book == null)
			return false;
		return bookMarkRepository.existsByUser_IdAndBook_SlugAndAudio_Id(user.getId(), bookSlug, id);
	}
	
	@Transactional
	@Override
	public Boolean deleteAudioMark(String bookSlug, Long id) {
		User user = getLoggedInUser();
		Book book = bookRepository.findOneBySlug(bookSlug).orElse(null);
		if (user == null || book == null)
			return false;
		if (bookMarkRepository.deleteByUser_IdAndBook_SlugAndAudio_Id(user.getId(), bookSlug, id) > 0) {
			return true;
		} else {
			return false;
		}
	}

	/*
	 * general
	 */
	private User getLoggedInUser() {
		return (User) session.getAttribute("user");
	}

	@Override
	public Page<BookMarkDTO> getBookmark(String type, String search, int page) {
		User user = getLoggedInUser();

		Pageable pageable = PageRequest.of(page, 4, Sort.by("id").descending());// page number , 4 items
		Page<BookMark> bookmarks = null;
		if (user == null)
			return null;

		switch (type) {
		case "online":
			bookmarks = bookMarkRepository
					.findAllByOnline_IdIsNotNullAndUser_IdAndBook_StatusAndBook_NameContainingIgnoreCase(user.getId(),
							1, search, pageable);
			break;

		case "audio":
			bookmarks = bookMarkRepository
					.findAllByAudio_IdIsNotNullAndUser_IdAndBook_StatusAndBook_NameContainingIgnoreCase(user.getId(), 1,
							search, pageable);
			break;

		case "pdf":
			bookmarks = bookMarkRepository
					.findAllByPdf_IdIsNotNullAndUser_IdAndBook_StatusAndBook_NameContainingIgnoreCase(user.getId(), 1,
							search, pageable);
			break;

		default:
			bookmarks = bookMarkRepository.findAllByUser_IdAndBook_StatusAndBook_NameContainingIgnoreCase(user.getId(),
					1, search, pageable);
		}

		if (bookmarks == null)
			return null;

		List<BookMarkDTO> bookDtos = bookmarks.getContent().stream().map(entity -> {
			BookMarkDTO dto = new BookMarkDTO();

			dto.setId(entity.getId());
			BookDTO bookDto = convertBookToDTO(entity.getBook());
			dto.setBook(bookDto);

			dto.setAudio(convertAudioToDto(entity.getAudio()));
			dto.setOnline(convertOnlineToDto(entity.getOnline()));
			dto.setPdf(convertPdfToDto(entity.getPdf()));

			return dto;
		}).collect(Collectors.toList());

		Page<BookMarkDTO> markDtos = new PageImpl<>(bookDtos, pageable, bookmarks.getTotalElements());
		return markDtos;
	}

	private PdfDTO convertPdfToDto(Pdf entity) {
		if (entity == null)
			return null;
		PdfDTO dto = new PdfDTO();
		BeanUtils.copyProperties(entity, dto);
		return dto;
	}

	private OnlineDTO convertOnlineToDto(Online entity) {
		if (entity == null)
			return null;
		OnlineDTO dto = new OnlineDTO();
		BeanUtils.copyProperties(entity, dto);
		return dto;
	}

	private AudioDTO convertAudioToDto(Audio entity) {
		if (entity == null)
			return null;
		AudioDTO dto = new AudioDTO();
		BeanUtils.copyProperties(entity, dto);
		return dto;
	}

	private BookDTO convertBookToDTO(Book entity) {
		if (entity == null)
			return null;
		BookDTO dto = new BookDTO();
		dto.setId(entity.getId());
		dto.setThumbnail(entity.getThumbnail());
		dto.setName(entity.getName());
		dto.setSlug(entity.getSlug());

		entity.getAuthors().forEach(author -> {
			AuthorDTO authorDTO = new AuthorDTO();
			authorDTO.setId(author.getId());
			authorDTO.setSlug(author.getSlug());
			authorDTO.setFullname(author.getFullname());
			dto.getAuthors().add(authorDTO);
		});

		entity.getCategories().forEach(category -> {
			BookCategoryDTO categoryDTO = new BookCategoryDTO();
			categoryDTO.setId(category.getId());
			categoryDTO.setSlug(category.getSlug());
			categoryDTO.setName(category.getName());
			dto.getCategories().add(categoryDTO);
		});
		return dto;
	}

	/*
	 * Online
	 */
	@Transactional
	@Override
	public Boolean addOnlineMark(String bookSlug, String chapterName) {
		User user = getLoggedInUser();
		Book book = bookRepository.findOneBySlug(bookSlug).orElse(null);
		if (user == null || book == null)
			return false;

		if (!bookMarkRepository.existsByUser_IdAndBook_SlugAndOnline_Name(user.getId(), bookSlug, chapterName)) {
			BookMark bookMark = new BookMark();
			bookMark.setBook(book);
			bookMark.setUser(user);
			Online online = onlineRepository.findByBook_SlugAndName(bookSlug, chapterName);
			bookMark.setOnline(online);
			bookMarkRepository.save(bookMark);
		}
		return true;
	}

	@Override
	public Boolean checkOnlineMark(String bookSlug, String chapterName) {
		User user = getLoggedInUser();
		Book book = bookRepository.findOneBySlug(bookSlug).orElse(null);
		if (user == null || book == null)
			return false;
		return bookMarkRepository.existsByUser_IdAndBook_SlugAndOnline_Name(user.getId(), bookSlug, chapterName);
	}

	@Transactional
	@Override
	public Boolean deleteOnlineMark(String bookSlug, String chapterName) {
		User user = getLoggedInUser();
		Book book = bookRepository.findOneBySlug(bookSlug).orElse(null);
		if (user == null || book == null)
			return false;
		if (bookMarkRepository.deleteByUser_IdAndBook_SlugAndOnline_Name(user.getId(), bookSlug, chapterName) > 0) {
			return true;
		} else {
			return false;
		}
	}

	@Transactional
	@Override
	public Boolean deleteOnlineMarkById(String bookSlug, Long id) {
		User user = getLoggedInUser();
		Book book = bookRepository.findOneBySlug(bookSlug).orElse(null);
		if (user == null || book == null)
			return false;
		if (bookMarkRepository.deleteByUser_IdAndBook_SlugAndOnline_Id(user.getId(), bookSlug, id) > 0) {
			return true;
		} else {
			return false;
		}
	}

	/*
	 * pdf
	 */
	@Transactional
	@Override
	public Boolean addPdfMark(String bookSlug, Long id) {
		User user = getLoggedInUser();
		Book book = bookRepository.findOneBySlug(bookSlug).orElse(null);
		if (user == null || book == null)
			return false;

		if (!bookMarkRepository.existsByUser_IdAndBook_SlugAndPdf_Id(user.getId(), bookSlug, id)) {
			BookMark bookMark = new BookMark();
			bookMark.setBook(book);
			bookMark.setUser(user);
			Pdf pdf = pdfRepository.findByBook_SlugAndId(bookSlug, id);
			bookMark.setPdf(pdf);
			bookMarkRepository.save(bookMark);
		}
		return true;
	}

	@Override
	public Boolean checkPdfMark(String bookSlug, Long id) {
		User user = getLoggedInUser();
		Book book = bookRepository.findOneBySlug(bookSlug).orElse(null);
		if (user == null || book == null)
			return false;
		return bookMarkRepository.existsByUser_IdAndBook_SlugAndPdf_Id(user.getId(), bookSlug, id);
	}

	@Transactional
	@Override
	public Boolean deletePdfMarkById(String bookSlug, Long id) {
		User user = getLoggedInUser();
		Book book = bookRepository.findOneBySlug(bookSlug).orElse(null);
		if (user == null || book == null)
			return false;
		if (bookMarkRepository.deleteByUser_IdAndBook_SlugAndPdf_Id(user.getId(), bookSlug, id) > 0) {
			return true;
		} else {
			return false;
		}
	}

}
